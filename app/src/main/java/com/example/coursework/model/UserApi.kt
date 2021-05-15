package com.example.coursework.model

import com.google.gson.annotations.SerializedName

data class UserApi(
    @SerializedName("email")
    val email : String,
    val password : String
)