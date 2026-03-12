package com.hilleliot.shop.ui.state

import com.hilleliot.shop.data.model.Product

data class CartItemUiState(
    val product: Product,
    val quantity: Int,
)
