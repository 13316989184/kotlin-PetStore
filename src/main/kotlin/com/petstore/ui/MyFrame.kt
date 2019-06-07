package com.petstore.ui

import java.awt.Toolkit
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame

open class MyFrame(title: String, width: Int, height: Int) : JFrame(title) {
    private val screenWidth = Toolkit.getDefaultToolkit().screenSize.getWidth()

    private val screenHeight = Toolkit.getDefaultToolkit().screenSize.getHeight()

    init {
        setSize(width, height)
        val x = (screenWidth - width).toInt() / 2
        val y = (screenHeight - height).toInt() / 2

        setLocation(x,y)

        addWindowListener(object :WindowAdapter(){
            override fun windowClosing(e: WindowEvent?) {
                System.exit(0)
            }
        })
    }
}