package com.rian.bodyfittest.data.network

import com.rian.bodyfittest.data.response.LoginResponse
import com.rian.bodyfittest.data.response.RegisterResponse
import com.rian.bodyfittest.data.response.TrainingResponseItem
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse


    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("exercises")
    @Headers("X-Api-Key:RgBZK69HCR4TO+L/AnCHyA==9USkNPzdMygWWdWW")
    fun getTraining(
    ): Call<ArrayList<TrainingResponseItem>>


}