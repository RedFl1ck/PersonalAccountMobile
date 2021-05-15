package com.example.coursework.repository

import com.example.coursework.api.RetrofitInstance
import com.example.coursework.model.UserApi
import retrofit2.Response

class Repository {

    suspend fun auth(userApi: UserApi): Response<UserApi> {
        return RetrofitInstance.api.auth(userApi)
    }
}