package com.petstore.dao

import com.petstore.domain.Order
import com.petstore.domain.OrderDetail

//订单明细管理DAO
interface OrderDetailDao {

    // 查询所有的订单明细信息
    fun findAll(): List<OrderDetail>

    // 根据主键查询订单明细信息
    fun findByPK(orderid: Int, productid: String): OrderDetail?

    // 创建订单明细信息
    fun create(orderDetail: OrderDetail)

    // 修改订单明细信息
    fun modify(orderDetail: OrderDetail)

    // 删除订单明细信息
    fun remove(orderDetail: OrderDetail)
}