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

import com.petstore.dao.mysql.ProductDaoImp
import com.petstore.domain.Product
import java.awt.*
import java.util.*
import javax.swing.*

//商品列表窗口
class ProductListFrame : MyFrame("商品列表", 1000, 700) {

    private var lblImage: JLabel? = null
    private var lblListprice: JLabel? = null
    private var lblDescn: JLabel? = null
    private var lblUnitcost: JLabel? = null
    // 初始化左侧面板中的表格控件
    private var table: JTable? = null

    // 商品列表集合
    private var products: List<Product>? = null
    // 创建商品Dao对象
    private val dao = ProductDaoImp()

    // 购物车，键是选择的商品Id，值是商品的数量
    private val cart = HashMap<String, Int>()
    // 选择的商品索引
    private var selectedRow = -1

    // 初始化搜索面板
    private val searchPanel: JPanel
        get() {

            val searchPanel = JPanel()
            with(searchPanel.layout as FlowLayout) {
                vgap = 20
                hgap = 40
            }

            with(JLabel("选择商品类别：")) {
                font = Font("微软雅黑", Font.PLAIN, 15)
                searchPanel.add(this)
            }

            val categorys = arrayOf("鱼类", "狗类", "爬行类", "猫类", "鸟类")
            val comboBox = JComboBox(categorys).apply {
                font = Font("微软雅黑", Font.PLAIN, 15)
                searchPanel.add(this)
            }

            val btnGo = JButton("查询").apply {
                font = Font("微软雅黑", Font.PLAIN, 15)
                searchPanel.add(this)
            }

            val btnReset = JButton("重置").apply {
                font = Font("微软雅黑", Font.PLAIN, 15)
                searchPanel.add(this)
            }

            // 注册查询按钮的ActionEvent事件监听器
            btnGo.addActionListener {
                val category = comboBox.selectedItem as String
                products = dao.findByCategory(category)
                println(products?.size)
                val model = ProductTableModel(products!!)
                table!!.model = model
            }
            // 注册重置按钮的ActionEvent事件监听器
            btnReset.addActionListener {
                products = dao.findAll()
                val model = ProductTableModel(products!!)
                table!!.model = model
            }
            return searchPanel
        }

    // 初始化右侧面板
    private val rightPanel: JPanel
        get() {

            val rightPanel = JPanel().apply {
                background = Color.WHITE
                layout = GridLayout(2, 1, 0, 0)
            }

            lblImage = JLabel().apply {
                horizontalAlignment = SwingConstants.CENTER
                rightPanel.add(this)
            }

            //detailPanel
            val detailPanel = JPanel().apply {
                background = Color.WHITE
                layout = GridLayout(8, 1, 0, 5)
                rightPanel.add(this)
            }
            //添加分隔线
            detailPanel.add(JSeparator())
            lblListprice = JLabel().apply {
                //lblListprice
                font = Font("微软雅黑", Font.PLAIN, 16)
                detailPanel.add(this)
            }

            lblUnitcost = JLabel().apply {
                //lblUnitcost
                font = Font("微软雅黑", Font.PLAIN, 16)
                detailPanel.add(this)
            }

            lblDescn = JLabel().apply {
                //lblDescn
                font = Font("微软雅黑", Font.PLAIN, 16)
                detailPanel.add(this)
            }
            //添加分隔线
            detailPanel.add(JSeparator())

            val btnAdd = JButton("添加到购物车").apply {
                font = Font("微软雅黑", Font.PLAIN, 15)
                detailPanel.add(this)
            }

            //添加一个空标签
            detailPanel.add(JLabel())

            val btnCheck = JButton("查看购物车").apply {
                font = Font("微软雅黑", Font.PLAIN, 15)
                detailPanel.add(this)
            }
            //注册【添加到购物车】按钮的ActionEvent事件监听器
            btnAdd.addActionListener {
                if (selectedRow < 0) {
                    return@addActionListener
                }
                val productid = products!![selectedRow].productid!!

                if (cart.containsKey(productid)) {
                    var quantity = cart[productid] as Int
                    cart.put(productid, ++quantity)
                } else {
                    cart.put(productid, 1)
                }
                println(cart)
            }
            btnCheck.addActionListener {
                CartFrame(cart, this).setVisible(true)
                isVisible = false
            }

            return rightPanel
        }

    // 初始化左侧面板
    private val leftPanel: JScrollPane
        get() {
            val leftScrollPane = JScrollPane()
            leftScrollPane.setViewportView(createTable())
            return leftScrollPane
        }

    // 初始化左侧面板中的表格控件
    private fun createTable(): JTable? {
        val model = ProductTableModel(products!!)
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

            val rowSelectionModel = table!!.selectionModel
            rowSelectionModel.addListSelectionListener { e ->
                //只处理鼠标释放
                if (e.valueIsAdjusting) {
                    return@addListSelectionListener
                }
                val lsm = e.source as ListSelectionModel
                selectedRow = lsm.minSelectionIndex
                if (selectedRow < 0) {
                    return@addListSelectionListener
                }
                // 更新右侧面板内容
                val (_, _, _, _, image, descn, listprice1, unitcost1) = products!![selectedRow]
                val petImage = "/images/$image"
                val icon = ImageIcon(ProductListFrame::class.java.getResource(petImage))
                lblImage?.icon = icon

                lblDescn!!.text = "商品描述：" + descn!!

                val listprice = listprice1.toDouble()
                val slistprice = String.format("商品市场价：%.2f", listprice)
                lblListprice!!.text = slistprice

                val unitcost = unitcost1.toDouble()
                val slblUnitcost = String.format("商品单价：%.2f", unitcost)
                lblUnitcost!!.text = slblUnitcost
            }
        } else {
            table!!.model = model
        }
        return table
    }

    //初始化代码块
    init {
        // 查询所有商品
        products = dao.findAll()

        // 添加顶部搜索面板
        contentPane.add(searchPanel, BorderLayout.NORTH)

        // 创建分栏面板
        with(JSplitPane()) {
            // 设置指定分隔条位置，从窗格的左边到分隔条的左边
            dividerLocation = 600
            // 设置左侧面板
            leftComponent = leftPanel
            // 设置右侧面板
            rightComponent = rightPanel
            // 把分栏面板添加到内容面板
            contentPane.add(this, BorderLayout.CENTER)
        }
    }
}
