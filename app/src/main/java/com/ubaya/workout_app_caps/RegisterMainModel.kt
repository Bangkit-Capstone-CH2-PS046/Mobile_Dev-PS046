package com.ubaya.workout_app_caps

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.ubaya.workout_app_caps.data.response.ErrorResponse
import com.ubaya.workout_app_caps.data.retrofit.ApiConfig
import retrofit2.HttpException

class RegisterMainModel : ViewModel() {

    suspend fun regUser(name: String, email: String, password: String): String? {
        try {
            //get success message
            val message = ApiConfig.getApiService().register(name, email, password).message
            return message
        } catch (e: HttpException) {
            //get error message
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
//            Log.d("Image File", errorMessage.toString())
            return errorMessage
        }

//        _isLoading.value = true
//        val client = ApiConfig.getApiService().register(name, email, password)
//        return client.error.toString()
//        client.enqueue(object : Callback<RegisterResponse> {
//            override fun onResponse(
//                call: Call<RegisterResponse>,
//                response: Response<RegisterResponse>
//            ) {
////                _isLoading.value = false
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//                    if (responseBody != null) {
////                        setRestaurantData(responseBody.restaurant)
////                        setUserData(responseBody.items)
////                        _listUsr.value = response.body()?.items
//
//                    }
//                } else {
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//            }
//            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
////                _isLoading.value = false
//                Log.e(TAG, "onFailure: ${t.message}")
//            }
//        })
    }


}