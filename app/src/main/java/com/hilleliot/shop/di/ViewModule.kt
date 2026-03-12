package com.hilleliot.shop.di

import com.hilleliot.shop.ui.viewmodel.AppViewModel
import com.hilleliot.shop.ui.viewmodel.CartViewModel
import com.hilleliot.shop.ui.viewmodel.CheckoutViewModel
import com.hilleliot.shop.ui.viewmodel.OnboardingViewModel
import com.hilleliot.shop.ui.viewmodel.OrderViewModel
import com.hilleliot.shop.ui.viewmodel.ProductDetailsViewModel
import com.hilleliot.shop.ui.viewmodel.ProductViewModel
import com.hilleliot.shop.ui.viewmodel.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel { AppViewModel(get()) }
    viewModel { CartViewModel(get(), get()) }
    viewModel { CheckoutViewModel(get(), get()) }
    viewModel { OnboardingViewModel(get()) }
    viewModel { OrderViewModel(get()) }
    viewModel { ProductDetailsViewModel(get(), get()) }
    viewModel { ProductViewModel(get(), get()) }
    viewModel { SplashViewModel(get()) }
}
