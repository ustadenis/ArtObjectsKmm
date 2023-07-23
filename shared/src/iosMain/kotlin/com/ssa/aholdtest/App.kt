package com.ssa.aholdtest

import com.ssa.aholdtest.di.appModule
import com.ssa.aholdtest.di.repoModule
import com.ssa.aholdtest.di.useCaseModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            appModule + repoModule + useCaseModule + viewModelModule
        )
    }
    Napier.base(DebugAntilog())
}