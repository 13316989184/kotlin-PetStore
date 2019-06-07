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
//代码文件：chapter28/PetStore/src/main/kotlin/com/a51work6/petstore/dao/mysql/AccountDaoImp.kt
package com.petstore.dao.mysql

import com.petstore.dao.IntegralDao
import com.petstore.domain.Integral
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

//用户管理DAO
class IntegralDaoImp : IntegralDao {
    override fun findById(id: String): Integral? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findAll(): List<Integral> {
//        var integralList: List<Integral> = emptyList()
        var integralList: List<Integral> = emptyList()
        //连接数据库
        Database.connect(URL, user = DB_USER, password = DB_PASSWORD, driver = DRIVER_CLASS)
        //操作数据库
        transaction {
            //添加SQL日志
            logger.addLogger(StdOutSqlLogger)
            integralList = Integrals.selectAll().map {
                val integral = Integral()
                integral.id = it[Integrals.id]
                integral.userid = it[Integrals.userid]
                integral.count = it[Integrals.count]
                integral.nopayfor = it[Integrals.nopayfor]
                integral
            }
        }
        return integralList
    }

    override fun create(integral: Integral) {
        TODO("not implemented")
    }

    override fun modify(integral: Integral) {
        TODO("not implemented")
    }

    override fun remove(integral: Integral) {
        TODO("not implemented")
    }

}
