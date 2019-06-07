package com.petstore.ui

import com.petstore.domain.Account
import java.util.*

object UserSession {
    var loginDate: Date? = null
    var account: Account? = null
}

fun main(args: Array<String>) {
    LoginFrame().isVisible = true
}