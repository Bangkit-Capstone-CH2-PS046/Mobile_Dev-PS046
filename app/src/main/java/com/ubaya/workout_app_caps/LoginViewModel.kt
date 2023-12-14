package com.ubaya.workout_app_caps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.ubaya.workout_app_caps.data.UserRepository
import com.ubaya.workout_app_caps.data.pref.UserModel
import com.ubaya.workout_app_caps.data.response.LoginResponse
import com.ubaya.workout_app_caps.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }
    suspend fun LogUser(email: String, password: String): String? {
        try {
            //get success message
            val bod_ = ApiConfig.getApiService().login(email, password)
            val message = bod_.message
            val token_ = bod_.loginResult!!.token
            saveSession(UserModel(email, token_!!))
            return message
        } catch (e: HttpException) {
            //get error message
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, LoginResponse::class.java)
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