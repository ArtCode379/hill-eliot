package com.hilleliot.shop.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hilleliot.shop.data.model.Product
import com.hilleliot.shop.data.model.ProductCategory
import com.hilleliot.shop.data.repository.CartRepository
import com.hilleliot.shop.data.repository.ProductRepository
import com.hilleliot.shop.ui.state.DataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
) : ViewModel() {
    private val _allProducts = MutableStateFlow<List<Product>>(emptyList())

    private val _selectedCategory = MutableStateFlow<ProductCategory?>(null)
    val selectedCategory: StateFlow<ProductCategory?> = _selectedCategory.asStateFlow()

    private val _productsState = MutableStateFlow<DataUiState<List<Product>>>(DataUiState.Initial)
    val productsState: StateFlow<DataUiState<List<Product>>> = _productsState.asStateFlow()

    init {
        viewModelScope.launch {
            productRepository.observeAll().collect { products ->
                _allProducts.value = products
                applyFilter()
            }
        }
    }

    fun selectCategory(category: ProductCategory?) {
        _selectedCategory.value = category
        applyFilter()
    }

    private fun applyFilter() {
        val filtered = if (_selectedCategory.value == null) {
            _allProducts.value
        } else {
            _allProducts.value.filter { it.category == _selectedCategory.value }
        }
        _productsState.update {
            if (filtered.isEmpty()) DataUiState.Empty else DataUiState.Populated(filtered)
        }
    }

    fun addToCart(productId: Int) {
        viewModelScope.launch { cartRepository.addOrIncrement(productId) }
    }
}
