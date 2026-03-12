package com.hilleliot.shop.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hilleliot.shop.data.repository.OnboardingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val onboardingRepository: OnboardingRepository,
) : ViewModel() {
    private val _onboardingSetState = MutableStateFlow(false)
    val onboardingSetState: StateFlow<Boolean> = _onboardingSetState.asStateFlow()

    fun completeOnboarding() {
        viewModelScope.launch {
            onboardingRepository.setOnboardingCompleted()
            _onboardingSetState.value = true
        }
    }
}
