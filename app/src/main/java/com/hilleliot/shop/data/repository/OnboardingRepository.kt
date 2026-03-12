package com.hilleliot.shop.data.repository

import com.hilleliot.shop.data.datastore.OnboardingDataStoreManager
import kotlinx.coroutines.flow.Flow

class OnboardingRepository(private val manager: OnboardingDataStoreManager) {
    val isOnboardingCompleted: Flow<Boolean> = manager.isOnboardingCompleted

    suspend fun setOnboardingCompleted() {
        manager.setOnboardingCompleted()
    }
}
