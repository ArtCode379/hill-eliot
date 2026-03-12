package com.hilleliot.shop.di

import com.hilleliot.shop.data.repository.CartRepository
import com.hilleliot.shop.data.repository.OnboardingRepository
import com.hilleliot.shop.data.repository.OrderRepository
import com.hilleliot.shop.data.repository.ProductRepository
import org.koin.dsl.module

val dataModule = module {
    single { ProductRepository() }
    single { CartRepository(get()) }
    single { OrderRepository(get()) }
    single { OnboardingRepository(get()) }
}
