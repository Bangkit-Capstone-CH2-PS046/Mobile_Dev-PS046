package com.rian.bodyfittest.data.response

import com.google.gson.annotations.SerializedName

data class TrainingResponse(
	@field:SerializedName("TrainingResponse")
	val trainingResponse: List<TrainingResponseItem>
)

data class TrainingResponseItem(

	@field:SerializedName("difficulty")
	val difficulty: String? = null,

	@field:SerializedName("instructions")
	val instructions: String? = null,

	@field:SerializedName("muscle")
	val muscle: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("equipment")
	val equipment: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)

