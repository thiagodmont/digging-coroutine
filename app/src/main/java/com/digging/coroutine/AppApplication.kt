package com.digging.coroutine

import androidx.multidex.MultiDexApplication
import com.digging.coroutine.di.commonModule
import com.digging.coroutine.di.networkModule
import com.digging.coroutine.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(
                networkModule,
                commonModule,
                viewModule
            )
        }
    }
}