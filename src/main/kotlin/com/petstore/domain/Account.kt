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
//代码文件：chapter28/PetStore/src/main/kotlin/com/a51work6/petstore/domain/Account.kt
package com.petstore.domain

data class Account(
        var userid: String? = null,     // 用户Id
        var username: String? = null,   // 用户名
        var password: String? = null,   // 用户密码
        var phone: String? = null,      // 电话号码
        var country: String? = null,    //国家
        var city: String? = null,       // 所在城市
        var addr: String? = null,       // 地址
        var email: String? = null      // 用户Email
)