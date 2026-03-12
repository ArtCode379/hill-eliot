package com.hilleliot.shop

import android.app.Application
import com.hilleliot.shop.di.dataModule
import com.hilleliot.shop.di.dataStoreModule
import com.hilleliot.shop.di.databaseModule
import com.hilleliot.shop.di.dispatcherModule
import com.hilleliot.shop.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HLELTApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@HLELTApp)
            modules(
                dataModule,
                dataStoreModule,
                databaseModule,
                dispatcherModule,
                viewModule,
            )
        }
    }
}
