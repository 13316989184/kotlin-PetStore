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
//代码文件：chapter28/PetStore/src/main/kotlin/com/a51work6/petstore/dao/mysql/OrderDetailDaoImp.kt
package com.petstore.dao.mysql

import com.petstore.dao.OrderDetailDao
import com.petstore.domain.Order
import com.petstore.domain.OrderDetail
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class OrderDetailDaoImp : OrderDetailDao {
    override fun findAll(): List<OrderDetail> {
        TODO("not implemented")
    }

    override fun findByPK(orderid: Int, productid: String): OrderDetail? {

        var orderDetailList: List<OrderDetail> = emptyList()
        //连接数据库
        Database.connect(URL, user = DB_USER, password = DB_PASSWORD, driver = DRIVER_CLASS)
        //操作数据库
        transaction {
            //添加SQL日志
            logger.addLogger(StdOutSqlLogger)
            //按照主键查询
            orderDetailList =
                Orderdetails.select { Orderdetails.orderid.eq(orderid) and Orderdetails.productid.eq(productid) }
                    .map {
                        val orderDetail = OrderDetail()
                        orderDetail.productid = it[Orderdetails.productid]
                        orderDetail.orderid = it[Orderdetails.orderid]
                        orderDetail.quantity = it[Orderdetails.quantity]
                        orderDetail.unitcost = it[Orderdetails.unitcost]
                        //Lambda表达式返回数据
                        orderDetail
                    }
        }
        return if (orderDetailList.isEmpty()) null else orderDetailList.first()
    }

    override fun create(orderDetail: OrderDetail) {
        //连接数据库
        Database.connect(URL, user = DB_USER, password = DB_PASSWORD, driver = DRIVER_CLASS)
        //操作数据库
        transaction {
            //添加SQL日志
            logger.addLogger(StdOutSqlLogger)
            Orderdetails.insert {
                it[orderid] = orderDetail.orderid
                it[productid] = orderDetail.productid!!
                it[quantity] = orderDetail.quantity
                it[unitcost] = orderDetail.unitcost
            }
        }
    }

    override fun modify(orderDetail: OrderDetail) {
        TODO("not implemented")
    }

    override fun remove(orderDetail: OrderDetail) {
        TODO("not implemented")
    }

}