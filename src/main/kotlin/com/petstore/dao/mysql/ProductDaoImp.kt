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
//代码文件：chapter28/PetStore/src/main/kotlin/com/a51work6/petstore/dao/mysql/ProductDaoImp.kt
package com.petstore.dao.mysql

import com.petstore.dao.ProductDao
import com.petstore.domain.Product
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class ProductDaoImp : ProductDao {
    
    override fun findAll(): List<Product> {
        var productList: List<Product> = emptyList()
        //连接数据库
        Database.connect(URL, user = DB_USER, password = DB_PASSWORD, driver = DRIVER_CLASS)
        //操作数据库
        transaction {
            //添加SQL日志
            logger.addLogger(StdOutSqlLogger)
            productList = Products.selectAll().map {
                val product = Product()
                product.productid = it[Products.productid]
                product.category = it[Products.category]
                product.cname = it[Products.cname]
                product.ename = it[Products.ename]
                product.image = it[Products.image]
                product.descn = it[Products.descn]
                product.listprice = it[Products.listprice]
                product.unitcost = it[Products.unitcost]
                //Lambda表达式返回数据
                product
            }
        }

        return productList
    }

    override fun findById(productid: String): Product? {
        var productList: List<Product> = emptyList()
        //连接数据库
        Database.connect(URL, user = DB_USER, password = DB_PASSWORD, driver = DRIVER_CLASS)
        //操作数据库
        transaction {
            //添加SQL日志
            logger.addLogger(StdOutSqlLogger)
            //按照主键查询
            productList = Products.select { Products.productid.eq(productid) }.map {
                val product = Product()
                product.productid = it[Products.productid]
                product.category = it[Products.category]
                product.cname = it[Products.cname]
                product.ename = it[Products.ename]
                product.image = it[Products.image]
                product.descn = it[Products.descn]
                product.listprice = it[Products.listprice]
                product.unitcost = it[Products.unitcost]
                //Lambda表达式返回数据
                product
            }
        }
        return if (productList.isEmpty()) null else productList.first()
    }

    override fun findByCategory(category: String): List<Product> {
        var productList: List<Product> = emptyList()
        //连接数据库
        Database.connect(URL, user = DB_USER, password = DB_PASSWORD, driver = DRIVER_CLASS)
        //操作数据库
        transaction {
            //添加SQL日志
            logger.addLogger(StdOutSqlLogger)
            //按照主键查询
            productList = Products.select { Products.category.eq(category) }.map {
                val product = Product()
                product.productid = it[Products.productid]
                product.category = it[Products.category]
                product.cname = it[Products.cname]
                product.ename = it[Products.ename]
                product.image = it[Products.image]
                product.descn = it[Products.descn]
                product.listprice = it[Products.listprice]
                product.unitcost = it[Products.unitcost]
                //Lambda表达式返回数据
                product
            }
        }
        return productList
    }

    override fun create(product: Product) {
        TODO("not implemented")
    }

    override fun modify(product: Product) {
        TODO("not implemented")
    }

    override fun remove(product: Product) {
        TODO("not implemented")
    }

}