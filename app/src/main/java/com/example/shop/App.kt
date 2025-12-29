package com.example.shop

import android.app.Application
import com.example.shop.UI.di.uiModule
import com.example.shop.data.di.dataModule
import com.example.shop.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(dataModule, uiModule, domainModule)
        }
    }
}
