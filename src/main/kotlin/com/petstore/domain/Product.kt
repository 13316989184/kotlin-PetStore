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

//代码文件：chapter28/PetStore/src/main/kotlin/com/a51work6/petstore/domain/Product.kt
package com.petstore.domain

import java.math.BigDecimal

data class Product(
    var productid: String? = null,// 商品Id
    var category: String? = null,// 商品类别
    var cname: String? = null,// 商品中文名
    var ename: String? = null,// 商品英文名
    var image: String? = null,// 商品描述
    var descn: String? = null,// 商品描述
    var listprice: BigDecimal = BigDecimal(0),// 商品市场价
    var unitcost: BigDecimal = BigDecimal(0)// 商品单价
)