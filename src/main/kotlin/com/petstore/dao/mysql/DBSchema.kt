package com.petstore.dao.mysql

import org.jetbrains.exposed.sql.Table

const val URL = "jdbc:mysql://localhost:3306/petstore?useSSL=false&verifyServer-Certificate=false"
const val DRIVER_CLASS = "com.mysql.jdbc.Driver"
const val DB_USER = "root"
const val DB_PASSWORD = "123456"

/** 用户表 **/
object Accounts : Table() {
    val userid = varchar("userid", length = 80).primaryKey()
    val password = varchar("password", length = 25)
    val email = varchar("email", length = 80)
    val name = varchar("name", length = 80)
    val addr = varchar("addr", length = 80)
    val city = varchar("city", length = 80)
    val country = varchar("country", length = 20)
    val phone = varchar("phone", length = 80)
}

/** 商品表**/
object Products : Table() {
    val productid = varchar("productid", length = 10).primaryKey()
    val category = varchar("category", length = 10)
    val cname = varchar("cname", length = 80)
    val ename = varchar("ename", length = 80)
    val image = varchar("image", length = 20)
    val descn = varchar("descn", length = 255)
    val listprice = decimal("listprice", 10, 2)
    val unitcost = decimal("unitcost", 10, 2)
}

/**订单表**/
object Orders : Table() {
    val orderid = long("orderid").primaryKey()
    val userid = varchar("userid", length = 80)
    val orderdate = datetime("orderdate")
    val status = integer("status")
    val amount = decimal("amount", 10, 2)
}

/***订单明细表 **/

object Orderdetails : Table() {
    val orderid = long("orderid").primaryKey()
    val productid = varchar("productid", length = 10).primaryKey()
    val quantity = integer("quantity")
    val unitcost = decimal("unitcost", 10, 2)
}