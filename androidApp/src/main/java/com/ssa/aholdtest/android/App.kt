package com.ssa.aholdtest.android

import android.app.Application
import com.ssa.aholdtest.di.appModule
import com.ssa.aholdtest.di.repoModule
import com.ssa.aholdtest.di.useCaseModule
import com.ssa.aholdtest.viewModelModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(
                appModule + repoModule + useCaseModule + viewModelModule
            )
        }
        Napier.base(DebugAntilog())
    }

}