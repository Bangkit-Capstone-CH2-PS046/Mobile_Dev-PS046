package com.rian.bodyfittest.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rian.bodyfittest.data.network.ApiService
import com.rian.bodyfittest.data.pref.Preference
import com.rian.bodyfittest.data.pref.UserModel
import com.rian.bodyfittest.data.response.LoginResponse
import com.rian.bodyfittest.data.response.RegisterResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository private constructor(
    private val preference: Preference, private val apiService: ApiService,


) {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    private var _loginResult = MutableLiveData<LoginResponse>()
    var loginResult: MutableLiveData<LoginResponse> = _loginResult

    suspend fun saveSession(user: UserModel) {
        preference.saveSession(user)
    }

    suspend fun register (username: String, email: String, password: String): RegisterResponse {
        return apiService.register(username, email, password)
    }

    fun getSession(): Flow<UserModel> {
        return preference.getSession()
    }

    fun login(email: String, password: String) {
        _isLoading.value = true
        val loginResponse = apiService.login(email, password)
        loginResponse.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _loginResult.value = response.body()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
            }
        })
    }

    suspend fun logout() {
        preference.logout()
    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            userPreference: Preference,
            apiService: ApiService,
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(userPreference, apiService)
            }.also { instance = it }
    }
}