package com.ubaya.workout_app_caps.data.pref

data class UserModel(
    val email: String,
    val token: String,
    val isLogin: Boolean = false
)