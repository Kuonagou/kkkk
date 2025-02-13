package com.example.kkkk

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class NoukApp : Application() {
    companion object {
        var instance: NoukApp? = null
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
        startKoin {
            androidContext(this@NoukApp)
        }
    }
}