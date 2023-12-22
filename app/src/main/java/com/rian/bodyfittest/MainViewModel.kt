package com.rian.bodyfittest

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rian.bodyfittest.data.pref.UserModel
import com.rian.bodyfittest.data.repository.Repository

class MainViewModel (private val repository: Repository) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}