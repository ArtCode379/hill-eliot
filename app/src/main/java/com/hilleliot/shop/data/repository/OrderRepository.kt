package com.hilleliot.shop.data.repository

import com.hilleliot.shop.data.dao.OrderDao
import com.hilleliot.shop.data.entity.OrderEntity
import kotlinx.coroutines.flow.Flow

class OrderRepository(private val orderDao: OrderDao) {
    fun observeAll(): Flow<List<OrderEntity>> = orderDao.observeAll()

    suspend fun insert(order: OrderEntity) {
        orderDao.insert(order)
    }
}
