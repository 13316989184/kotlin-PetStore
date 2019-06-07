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

import com.petstore.dao.AccountDao
import com.petstore.domain.Account
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

//用户管理DAO
class AccountDaoImp : AccountDao {

    override fun findAll(): List<Account> {
        TODO("not implemented")
    }

    override fun findById(userid: String): Account? {
        var accountList: List<Account> = emptyList()
        //连接数据库
        Database.connect(URL, user = DB_USER, password = DB_PASSWORD, driver = DRIVER_CLASS)
        //操作数据库
        transaction {
            //添加SQL日志
            logger.addLogger(StdOutSqlLogger)
            //按照主键查询
            accountList = Accounts.select { Accounts.userid.eq(userid) }.map {
                val account = Account()
                account.userid = it[Accounts.userid]
                account.password = it[Accounts.password]
                account.email = it[Accounts.email]
                account.username = it[Accounts.name]
                account.addr = it[Accounts.addr]
                account.city = it[Accounts.city]
                account.country = it[Accounts.country]
                account.phone = it[Accounts.phone]
                //Lambda表达式返回数据
                account
            }
        }
        return if (accountList.isEmpty()) null else accountList.first()
    }

    override fun create(account: Account) {
        TODO("not implemented")
    }

    override fun modify(account: Account) {
        TODO("not implemented")
    }

    override fun remove(account: Account) {
        TODO("not implemented")
    }

}
