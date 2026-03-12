package com.hilleliot.shop.data.repository

import com.hilleliot.shop.data.dao.CartItemDao
import com.hilleliot.shop.data.entity.CartItemEntity
import kotlinx.coroutines.flow.Flow

class CartRepository(private val cartItemDao: CartItemDao) {
    fun observeAll(): Flow<List<CartItemEntity>> = cartItemDao.observeAll()

    suspend fun addOrIncrement(productId: Int) {
        val existing = cartItemDao.getById(productId)
        if (existing == null) {
            cartItemDao.insert(CartItemEntity(id = productId, quantity = 1))
        } else {
            cartItemDao.update(existing.copy(quantity = existing.quantity + 1))
        }
    }

    suspend fun decrement(productId: Int) {
        val existing = cartItemDao.getById(productId) ?: return
        if (existing.quantity <= 1) {
            cartItemDao.deleteById(productId)
        } else {
            cartItemDao.update(existing.copy(quantity = existing.quantity - 1))
        }
    }

    suspend fun deleteById(productId: Int) {
        cartItemDao.deleteById(productId)
    }

    suspend fun deleteAll() {
        cartItemDao.deleteAll()
    }
}
