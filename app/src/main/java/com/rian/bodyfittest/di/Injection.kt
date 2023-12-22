package com.rian.bodyfittest.di

import android.content.Context
import com.rian.bodyfittest.data.network.ApiConfig
import com.rian.bodyfittest.data.pref.Preference
import com.rian.bodyfittest.data.pref.dataStore
import com.rian.bodyfittest.data.repository.Repository

object Injection {
    fun provideRepository(context: Context): Repository {
        val pref = Preference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()

        return Repository.getInstance(pref, apiService)
    }
}