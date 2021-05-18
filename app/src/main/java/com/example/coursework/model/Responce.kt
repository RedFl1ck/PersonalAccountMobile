package com.example.coursework.model

import com.google.gson.annotations.SerializedName

class MineUserEntity {

    data class MineUserInfo(
        val result: Result,
        val message: String,
        val success: Boolean,
        val statusCode: Int,
    )

    data class Result(
        val id: Int,
        val name: String,
        val surname: String,
        val middleName: String,
        val birthday: String,
        val email: String,
        val specialty: String,
        val groupName: Any,
        val courseNumber: Any,
        val status: Int,
    )
}

data class Posts(
    val result : List<Post>,
    val message : Any?,
    val success : Boolean,
    val statusCode : Int
)

data class Post(
    val id: Int,
    val description: String,
    val title: String
    )

data class Responce(
    val errors : List<String>,
    val message : String,
    val success : Boolean,
    val statusCode : Int
)