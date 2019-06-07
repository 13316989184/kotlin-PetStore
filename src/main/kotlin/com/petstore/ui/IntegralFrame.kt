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
//代码文件：chapter28/PetStore/src/main/kotlin/com/a51work6/petstore/ui/ProductListFrame.kt
package com.petstore.ui

import com.petstore.dao.mysql.IntegralDaoImp
import com.petstore.dao.mysql.ProductDaoImp
import com.petstore.domain.Integral
import com.petstore.domain.Product
import java.awt.*
import java.util.*
import javax.swing.*

//商品列表窗口
class IntegralFrame : MyFrame("个人信息", 1000, 700) {

    private var lblImage: JLabel? = null
    private var lblListprice: JLabel? = null
    private var lblDescn: JLabel? = null
    private var lblUnitcost: JLabel? = null
    // 初始化左侧面板中的表格控件
    private var table: JTable? = null
    // 商品列表集合
    private var integral: List<Integral>? = null
    // 创建商品Dao对象
    private val dao = IntegralDaoImp()
    // 初始化左侧面板
    private val leftPanel: JScrollPane
        get() {
            val leftScrollPane = JScrollPane()
            leftScrollPane.setViewportView(createTable())
            return leftScrollPane
        }

    // 初始化左侧面板中的表格控件
    private fun createTable(): JTable? {
        val model = IntegralModel(integral!!)
        if (table == null) {
            table = JTable(model).apply {
                // 设置表中内容字体
                font = Font("微软雅黑", Font.PLAIN, 16)
                // 设置表列标题字体
                tableHeader.font = Font("微软雅黑", Font.BOLD, 16)
                // 设置表行高
                rowHeight = 51
                rowSelectionAllowed = true
                setSelectionMode(ListSelectionModel.SINGLE_SELECTION)
            }
        } else {
            table!!.model = model
        }
        return table
    }

    //初始化代码块
    init {
        // 查询所有商品
        integral = dao.findAll()
        // 创建分栏面板
        with(JSplitPane()) {
            // 设置指定分隔条位置，从窗格的左边到分隔条的左边
            dividerLocation = 600
            // 设置左侧面板
            leftComponent = leftPanel
            // 把分栏面板添加到内容面板
            contentPane.add(this, BorderLayout.CENTER)
        }
    }
}
