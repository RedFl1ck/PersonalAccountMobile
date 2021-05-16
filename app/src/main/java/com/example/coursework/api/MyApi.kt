package com.example.coursework.api

import com.example.coursework.model.MineUserEntity
import com.example.coursework.model.RegisterApi
import com.example.coursework.model.Responce
import com.example.coursework.model.UserApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyApi {

    @POST("auth")
    suspend fun auth(
        @Body userApi: UserApi
    ): Response<MineUserEntity.MineUserInfo>

    @POST("register")
    suspend fun register(
        @Body registerApi: RegisterApi
    ): Response<Responce>
}