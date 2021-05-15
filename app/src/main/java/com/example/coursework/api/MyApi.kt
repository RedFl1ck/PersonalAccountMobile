package com.example.coursework.api

import com.example.coursework.model.UserApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MyApi {

    @POST("auth")
    suspend fun auth(
        @Body userApi: UserApi
    ): Response<UserApi>
}