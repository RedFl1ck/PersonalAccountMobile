package com.example.coursework.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://moveit.ru.com:40073/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: MyApi by lazy {
        retrofit.create(MyApi::class.java)
    }
}