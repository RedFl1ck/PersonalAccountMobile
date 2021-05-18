package com.example.coursework.repository

import com.example.coursework.api.RetrofitInstance
import com.example.coursework.model.*
import retrofit2.Response

class Repository {

    suspend fun auth(userApi: UserApi): Response<MineUserEntity.MineUserInfo> {
        return RetrofitInstance.api.auth(userApi)
    }

    suspend fun register(registerApi: RegisterApi): Response<Responce> {
        return RetrofitInstance.api.register(registerApi)
    }

    suspend fun getPosts(): Response<Posts> {
        return RetrofitInstance.api.getPosts()
    }
}