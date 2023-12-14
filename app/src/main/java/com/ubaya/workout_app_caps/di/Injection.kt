package com.ubaya.workout_app_caps.di

import android.content.Context
import com.ubaya.workout_app_caps.data.UserRepository
import com.ubaya.workout_app_caps.data.pref.UserPreference
import com.ubaya.workout_app_caps.data.pref.dataStore
import com.ubaya.workout_app_caps.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()

        return UserRepository.getInstance(pref, apiService)
    }
}