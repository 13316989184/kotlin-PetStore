package com.petstore.domain

import java.math.BigDecimal

//订单明细
data class OrderDetail(
    var orderid: Long = 0,          // 订单Id
    var productid: String? = null,  // 商品Id
    var quantity: Int = 0,          // 商品数量
    var unitcost: BigDecimal = BigDecimal(0)   // 单价
)