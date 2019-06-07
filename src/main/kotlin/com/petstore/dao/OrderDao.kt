package com.petstore.dao

import com.petstore.domain.Account
import com.petstore.domain.Order

interface OrderDao {
    fun findAll(): List<Order>

    fun findById(orderid: String): Order?

    fun create(order: Order)

    fun modify(order: Order)

    fun remove(order: Order)
}