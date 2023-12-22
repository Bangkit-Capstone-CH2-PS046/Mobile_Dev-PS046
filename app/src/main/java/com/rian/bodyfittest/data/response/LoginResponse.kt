package com.rian.bodyfittest.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("LoginResult")
    val loginResult: LoginResult? = null,

    @field:SerializedName("Error")
    val error: Boolean? = null,

    @field:SerializedName("Message")
    val message: String? = null
)

data class LoginResult(

    @field:SerializedName("Username")
    val username: String? = null,

    @field:SerializedName("Email")
    val email: String? = null,

    @field:SerializedName("Token")
    val token: String? = null
)