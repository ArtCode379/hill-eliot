package com.hilleliot.shop.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hilleliot.shop.data.dao.CartItemDao
import com.hilleliot.shop.data.dao.OrderDao
import com.hilleliot.shop.data.database.converter.Converters
import com.hilleliot.shop.data.entity.CartItemEntity
import com.hilleliot.shop.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class HLELTDatabase : RoomDatabase() {
    abstract fun cartItemDao(): CartItemDao
    abstract fun orderDao(): OrderDao
}
