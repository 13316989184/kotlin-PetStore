/*
* Created by 智捷课堂
* 本书网站：www.51work6.com 
* 智捷课堂在线视频：www.zhijieketang.com
* 智捷课堂微信公共号：zhijieketang
* 邮箱：eorient@sina.com
* Java&Kotlin读者服务QQ群：547370999
* 【配套电子书】网址：
*       百度阅读：
*       https://yuedu.baidu.com/ebook/9ab2a82bf342336c1eb91a37f111f18583d00ca2
*/
//代码文件：chapter28/PetStore/src/main/kotlin/com/a51work6/petstore/dao/mysql/OrderDaoImp.kt
package com.petstore.dao.mysql

import com.petstore.dao.OrderDao
import com.petstore.domain.Order
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

class OrderDaoImp : OrderDao {
    override fun findById(orderid: String): Order? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findAll(): List<Order> {

        var orderList: List<Order> = emptyList()
        //连接数据库
        Database.connect(URL, user = DB_USER, password = DB_PASSWORD, driver = DRIVER_CLASS)
        //操作数据库
        transaction {
            //添加SQL日志
            logger.addLogger(StdOutSqlLogger)
            orderList = Orders.selectAll().map {
                val order = Order()
                order.orderid = it[Orders.orderid]
                order.userid = it[Orders.userid]
                order.orderdate = it[Orders.orderdate].toDate()
                order.status = it[Orders.status]
                order.amount = it[Orders.amount]
                //Lambda表达式返回数据
                order
            }
        }
        return orderList
    }
    override fun create(order: Order) {
        //连接数据库
        Database.connect(URL, user = DB_USER, password = DB_PASSWORD, driver = DRIVER_CLASS)
        //操作数据库
        transaction {
            //添加SQL日志
            logger.addLogger(StdOutSqlLogger)
            Orders.insert {
                it[Orders.orderid] = order.orderid
                it[Orders.userid] = order.userid!!
                it[Orders.orderdate] = DateTime.now()//DateTime(order.orderdate)
                it[Orders.status] = order.status
                it[Orders.amount] = order.amount
            }
        }
    }

    override fun modify(order: Order) {
        TODO("not implemented")
    }

    override fun remove(order: Order) {
        TODO("not implemented")
    }
}