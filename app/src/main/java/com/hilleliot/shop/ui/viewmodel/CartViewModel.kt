package com.hilleliot.shop.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hilleliot.shop.data.repository.CartRepository
import com.hilleliot.shop.data.repository.ProductRepository
import com.hilleliot.shop.ui.state.CartItemUiState
import com.hilleliot.shop.ui.state.DataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository,
) : ViewModel() {
    private val _cartItemsState = MutableStateFlow<DataUiState<List<CartItemUiState>>>(DataUiState.Initial)
    val cartItemsState: StateFlow<DataUiState<List<CartItemUiState>>> = _cartItemsState.asStateFlow()

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice: StateFlow<Double> = _totalPrice.asStateFlow()

    init {
        viewModelScope.launch {
            cartRepository.observeAll().collect { entities ->
                val items = entities.mapNotNull { entity ->
                    productRepository.getById(entity.id)?.let { product ->
                        CartItemUiState(product = product, quantity = entity.quantity)
                    }
                }
                _cartItemsState.update {
                    if (items.isEmpty()) DataUiState.Empty else DataUiState.Populated(items)
                }
                _totalPrice.update { items.sumOf { it.product.price * it.quantity } }
            }
        }
    }

    fun incrementProductInCart(productId: Int) {
        viewModelScope.launch { cartRepository.addOrIncrement(productId) }
    }

    fun decrementItemInCart(productId: Int) {
        viewModelScope.launch { cartRepository.decrement(productId) }
    }

    fun deleteItemFromCart(productId: Int) {
        viewModelScope.launch { cartRepository.deleteById(productId) }
    }
}
