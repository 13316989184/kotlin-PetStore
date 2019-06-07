package com.petstore.dao

import com.petstore.domain.Account

interface AccountDao {
    fun findAll(): List<Account>

    fun findById(userid: String): Account?

    fun create(account: Account)

    fun modify(account: Account)

    fun remove(account: Account)
}