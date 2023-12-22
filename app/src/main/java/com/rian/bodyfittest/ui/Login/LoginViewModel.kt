package com.rian.bodyfittest.ui.Login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.rian.bodyfittest.data.network.ApiConfig
import com.rian.bodyfittest.data.pref.UserModel
import com.rian.bodyfittest.data.repository.Repository
import com.rian.bodyfittest.data.response.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel (private val repository: Repository) : ViewModel() {
    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }
    var loginResult : MutableLiveData<LoginResponse> = repository.loginResult
    var isLoading: LiveData<Boolean> = repository.isLoading

    fun login(email: String, password: String) {
        return repository.login(email, password)
    }
}