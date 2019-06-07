package com.petstore.dao

import com.petstore.domain.Integral

interface IntegralDao {
    fun findAll(): List<Integral>

    fun findById(id: String): Integral?

    fun create(integral: Integral)

    fun modify(integral: Integral)

    fun remove(integral: Integral)
}