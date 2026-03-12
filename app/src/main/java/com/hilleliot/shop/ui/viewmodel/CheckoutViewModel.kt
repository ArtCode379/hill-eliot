package com.hilleliot.shop.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hilleliot.shop.data.entity.OrderEntity
import com.hilleliot.shop.data.repository.CartRepository
import com.hilleliot.shop.data.repository.OrderRepository
import com.hilleliot.shop.data.repository.ProductRepository
import com.hilleliot.shop.ui.state.DataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID

class CheckoutViewModel(
    private val cartRepository: CartRepository,
    private val orderRepository: OrderRepository,
) : ViewModel() {
    var customerFirstName by mutableStateOf("")
        private set
    var customerLastName by mutableStateOf("")
        private set
    var customerEmail by mutableStateOf("")
        private set

    private val _emailInvalidState = MutableStateFlow(false)
    val emailInvalidState: StateFlow<Boolean> = _emailInvalidState.asStateFlow()

    private val _orderState = MutableStateFlow<DataUiState<OrderEntity>>(DataUiState.Initial)
    val orderState: StateFlow<DataUiState<OrderEntity>> = _orderState.asStateFlow()

    fun updateCustomerFirstName(value: String) { customerFirstName = value }
    fun updateCustomerLastName(value: String) { customerLastName = value }
    fun updateCustomerEmail(value: String) {
        customerEmail = value
        _emailInvalidState.value = false
    }

    fun placeOrder() {
        if (!isEmailValid(customerEmail)) {
            _emailInvalidState.value = true
            return
        }
        viewModelScope.launch {
            val cartItems = cartRepository.observeAll().first()
            val totalPrice = cartItems.sumOf { it.quantity.toDouble() }
            val orderNumber = "HE-${UUID.randomUUID().toString().take(8).uppercase()}"
            val description = cartItems.joinToString(", ") { "Item x ${it.quantity}" }

            val order = OrderEntity(
                orderNumber = orderNumber,
                description = description,
                customerFirstName = customerFirstName,
                customerLastName = customerLastName,
                customerEmail = customerEmail,
                price = totalPrice,
                timestamp = LocalDateTime.now(),
            )
            orderRepository.insert(order)
            cartRepository.deleteAll()
            _orderState.update { DataUiState.Populated(order) }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
