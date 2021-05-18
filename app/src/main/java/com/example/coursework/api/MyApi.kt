package com.example.coursework.api

import com.example.coursework.model.*
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

    @GET("posts")
    suspend fun getPosts(): Response<Posts>
}