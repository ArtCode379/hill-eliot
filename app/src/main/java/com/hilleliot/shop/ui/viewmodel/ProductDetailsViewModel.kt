package com.hilleliot.shop.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hilleliot.shop.data.model.Product
import com.hilleliot.shop.data.repository.CartRepository
import com.hilleliot.shop.data.repository.ProductRepository
import com.hilleliot.shop.ui.state.DataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
) : ViewModel() {
    private val _productDetailsState = MutableStateFlow<DataUiState<Product>>(DataUiState.Initial)
    val productDetailsState: StateFlow<DataUiState<Product>> = _productDetailsState.asStateFlow()

    private var currentProduct: Product? = null

    fun observeProductDetails(productId: Int) {
        viewModelScope.launch {
            productRepository.observeById(productId).collect { product ->
                _productDetailsState.update {
                    if (product != null) {
                        currentProduct = product
                        DataUiState.Populated(product)
                    } else {
                        DataUiState.Empty
                    }
                }
            }
        }
    }

    fun addProductToCart() {
        val product = currentProduct ?: return
        viewModelScope.launch { cartRepository.addOrIncrement(product.id) }
    }
}
