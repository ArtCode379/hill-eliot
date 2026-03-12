package com.hilleliot.shop.di

import androidx.room.Room
import com.hilleliot.shop.data.database.HLELTDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            HLELTDatabase::class.java,
            "hlelt_database"
        ).build()
    }
    single { get<HLELTDatabase>().cartItemDao() }
    single { get<HLELTDatabase>().orderDao() }
}
