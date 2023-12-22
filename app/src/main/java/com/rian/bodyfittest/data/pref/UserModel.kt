package com.rian.bodyfittest.data.pref

data class UserModel(
    val username: String,
    val email: String,
    val token: String,
    val isLogin: Boolean = false
)