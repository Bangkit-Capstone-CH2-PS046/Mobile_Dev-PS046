package com.rian.bodyfittest.ui.Register

import androidx.lifecycle.ViewModel
import com.rian.bodyfittest.data.repository.Repository
import com.rian.bodyfittest.data.response.RegisterResponse

class RegisterViewModel (private val repository: Repository) : ViewModel() {

    suspend fun register(username: String, email: String, password: String): RegisterResponse {
        return repository.register(username, email, password)
    }



}