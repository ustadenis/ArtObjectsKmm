package com.ssa.aholdtest.base

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

abstract class BaseViewModel<TEvent> : IBaseViewModel<TEvent>, KMMViewModel() {

    private val actor = MutableSharedFlow<TEvent>(replay = 1, extraBufferCapacity = 1)

    init {
        actor.onEach { event ->
            viewModelScope.coroutineScope.launch {
                try {
                    onEvent(event)
                } catch (ex: Exception) {
                    onError(ex, event)
                }
            }
        }.launchIn(viewModelScope.coroutineScope)
    }

    abstract suspend fun onEvent(event: TEvent)

    override fun sendEvent(event: TEvent): Boolean {
        return actor.tryEmit(event)
    }

    protected open fun onError(exception: Throwable, event: TEvent) {
        if (exception is CancellationException) return
        Napier.e { "$event: $exception" }
    }
}

interface IBaseViewModel<TEvent> {
    fun sendEvent(event: TEvent): Boolean
}