package com.rian.bodyfittest.ui.Profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rian.bodyfittest.data.pref.UserModel
import com.rian.bodyfittest.data.repository.Repository
import kotlinx.coroutines.launch

class ProfileViewModel (private val repository: Repository) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

}