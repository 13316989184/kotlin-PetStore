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
//代码文件：chapter28/PetStore/src/main/kotlin/com/a51work6/petstore/ui/CartFrame.kt
package com.petstore.ui

import com.petstore.dao.mysql.OrderDaoImp
import com.petstore.dao.mysql.OrderDetailDaoImp
import com.petstore.dao.mysql.ProductDaoImp
import com.petstore.domain.Order
import com.petstore.domain.OrderDetail
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.Font
import java.math.BigDecimal
import java.util.*
import javax.swing.*


//商品购物车窗口
class CartFrame(private val cart: MutableMap<String, Int>, // 购物车，键是选择的商品Id，值是商品的数量
                private val productListFrame: ProductListFrame // 引用到上级Frame（ProductListFrame）
) : MyFrame("商品购物车", 1000, 700) {

    private var table: JTable? = null
    // 购物车数据
    private var data = Array(cart.size) {
        arrayOfNulls<Any>(5)
    }
    // 创建商品Dao对象
    private val dao = ProductDaoImp()
    // 计算订单应付总金额
    private val orderTotalAmount: BigDecimal
        get() {
            var totalAmount = BigDecimal(0.0)
            for (i in data.indices) {
                totalAmount += data[i][4] as BigDecimal
            }
            return totalAmount
        }

    //初始化代码块
    init {

        val topPanel = JPanel()
        val flowLayout = topPanel.layout as FlowLayout
        flowLayout.vgap = 10
        flowLayout.hgap = 20
        contentPane.add(topPanel, BorderLayout.NORTH)

        val btnReturn = JButton("返回商品列表").apply {
            font = Font("微软雅黑", Font.PLAIN, 15)
            topPanel.add(this)
        }

        val btuSubmit = JButton("提交订单").apply {
            font = Font("微软雅黑", Font.PLAIN, 15)
            topPanel.add(this)
        }

        with(JScrollPane()) {
            setViewportView(createTable())
            contentPane.add(this, BorderLayout.CENTER)
        }

        // 注册【提交订单】按钮的ActionEvent事件监听器
        btuSubmit.addActionListener {
            // 生成订单
            generateOrders()

            val label = JLabel("订单已经生成，等待付款。")
            label.font = Font("微软雅黑", Font.PLAIN, 15)
            if (JOptionPane.showConfirmDialog(this, label, "信息", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                // TODO 付款
                System.exit(0)
            } else {
                System.exit(0)
            }
        }

        // 注册【返回商品列表】按钮的ActionEvent事件监听器
        btnReturn.addActionListener {
            // 更新购物车
            for (i in data.indices) {
                // 商品编号
                val productid = data[i][0] as String
                // 数量
                val quantity = data[i][3] as Int
                cart[productid] = quantity
            }
            productListFrame.isVisible = true
            isVisible = false
        }
    }

    // 初始化左侧面板中的表格控件
    private fun createTable(): JTable? {

        val keys = this.cart.keys
        var idx = 0
        for (productid in keys) {
            val p = dao.findById(productid)!!
            data[idx][0] = p.productid// 商品编号
            data[idx][1] = p.cname// 商品名

            data[idx][2] = p.unitcost// 商品单价
            data[idx][3] = cart[productid]// 数量
            // 计算商品应付金额
            val amount = p.unitcost * BigDecimal(cart[productid]!!)
            data[idx][4] = amount
            idx++
        }

        // 创建表数据模型
        val model = CartTableModel(data)

        if (table == null) {
            // 创建表
            table = JTable(model).apply {
                // 设置表中内容字体
                font = Font("微软雅黑", Font.PLAIN, 16)
                // 设置表列标题字体
                tableHeader.font = Font("微软雅黑", Font.BOLD, 16)
                // 设置表行高
                rowHeight = 51
                rowSelectionAllowed = false
            }
        } else {
            table!!.model = model
        }
        return table
    }

    // 生成订单
    private fun generateOrders() {

        val orderDao = OrderDaoImp()
        val orderDetailDao = OrderDetailDaoImp()

        // 订单Id是当前时间
        val now = Date()
        val orderId = now.getTime()
        // 设置订单属性
        val order = Order().apply {
            userid = UserSession.account!!.userid
            // 0待付款
            status = 0
            orderid = orderId
            orderdate = now
            amount = orderTotalAmount
        }
        // 下订单时间是数据库自动生成不用设置
        // 创建订单
        orderDao.create(order)

        for (i in data.indices) {

            val orderDetail = OrderDetail().apply {
                orderid = order.orderid
                productid = data[i][0] as String
                quantity = data[i][3] as Int
                unitcost = data[i][2] as BigDecimal
            }
            // 创建订单详细
            orderDetailDao.create(orderDetail)
        }
    }
}
