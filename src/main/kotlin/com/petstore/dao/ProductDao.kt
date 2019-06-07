package com.petstore.dao

import com.petstore.domain.Product

interface ProductDao {
    fun findAll(): List<Product>

    // 根据主键查询商品信息
    fun findById(productid: String): Product?

    // 根据按照列表查询商品信息
    fun findByCategory(category: String): List<Product>

    // 创建商品信息
    fun create(product: Product)

    // 修改商品信息
    fun modify(product: Product)

    // 删除商品信息
    fun remove(product: Product)
}