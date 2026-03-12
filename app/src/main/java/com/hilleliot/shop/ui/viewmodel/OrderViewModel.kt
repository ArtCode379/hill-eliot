package com.hilleliot.shop.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hilleliot.shop.data.entity.OrderEntity
import com.hilleliot.shop.data.repository.OrderRepository
import com.hilleliot.shop.ui.state.DataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OrderViewModel(
    private val orderRepository: OrderRepository,
) : ViewModel() {
    private val _ordersState = MutableStateFlow<DataUiState<List<OrderEntity>>>(DataUiState.Initial)
    val ordersState: StateFlow<DataUiState<List<OrderEntity>>> = _ordersState.asStateFlow()

    init {
        viewModelScope.launch {
            orderRepository.observeAll().collect { orders ->
                _ordersState.update {
                    if (orders.isEmpty()) DataUiState.Empty else DataUiState.Populated(orders)
                }
            }
        }
    }
}
