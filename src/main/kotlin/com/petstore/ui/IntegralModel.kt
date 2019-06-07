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
//代码文件：chapter28/PetStore/src/main/kotlin/com/a51work6/petstore/ui/ProductTableModel.kt
package com.petstore.ui

import com.petstore.domain.Integral
import javax.swing.table.AbstractTableModel

//商品列表表格模型
class IntegralModel(val data: List<Integral>) : AbstractTableModel() {

    // 表格列名columnNames
    private val columnNames = arrayOf("编号", "总金额", "用户", "是否禁用")

    // 返回列数
    override fun getColumnCount() = columnNames.size

    // 返回行数
    override fun getRowCount() = data.size

    // 获得某行某列的数据，而数据保存在对象数组data中
    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any? {

        // 每一行就是一个Product商品对象
        val (id, count, userid, nopayfor) = data[rowIndex]

        return when (columnIndex) {
            0 -> id // 第一列商品编号
            1 -> count // 第二列商品类别
            2 -> userid // 第三列商品中文名
            else -> nopayfor // 第四列商品英文名
        }
    }

    override fun getColumnName(columnIndex: Int) = columnNames[columnIndex]
}
