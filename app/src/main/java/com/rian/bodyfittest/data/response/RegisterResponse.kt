package com.rian.bodyfittest.data.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("Status")
	val status: Boolean? = null,

	@field:SerializedName("Message")
	val message: String? = null
)
