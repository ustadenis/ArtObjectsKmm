package com.ssa.aholdtest

import androidx.annotation.CallSuper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
@OptIn(DelicateCoroutinesApi::class)
abstract class BaseVmTest {

    protected val timeout: Long = 1500L
    protected val delay: Long = 500L

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newFixedThreadPoolContext(5, this::class.java.simpleName)

    @Before
    @CallSuper
    open fun before() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    @CallSuper
    open fun after() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    object TestException : Exception()

}

val BaseVmTest.testViewModelScope: CoroutineScope get() = TestCoroutineScope()