package com.rian.bodyfittest.ui.Home

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rian.bodyfittest.data.network.ApiConfigTraining
import com.rian.bodyfittest.data.pref.UserModel
import com.rian.bodyfittest.data.repository.Repository
import com.rian.bodyfittest.data.response.TrainingResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel (private val repository: Repository) : ViewModel(){
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _listTrn = MutableLiveData<ArrayList<TrainingResponseItem>>()
    val lisTrn: LiveData<ArrayList<TrainingResponseItem>> = _listTrn


    init {
        showTrain()
    }

    fun showTrain() {
        _isLoading.value = true
        val client = ApiConfigTraining.getApiService().getTraining()
        client.enqueue(object : Callback<ArrayList<TrainingResponseItem>> {
            override fun onResponse(
                call: Call<ArrayList<TrainingResponseItem>>,
                response: Response<ArrayList<TrainingResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _listTrn.value = response.body()

                    }
                } else {
                    _isLoading.value = false
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ArrayList<TrainingResponseItem>>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }

}