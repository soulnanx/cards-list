package com.hivecode.hearthstonecards

import android.app.Application
import com.hivecode.hearthstonecards.di.repositoryModule
import com.hivecode.hearthstonecards.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            androidFileProperties()
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule

                )
            )
        }
    }

}