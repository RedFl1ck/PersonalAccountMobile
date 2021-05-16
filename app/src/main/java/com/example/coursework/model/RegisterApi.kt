package com.example.coursework.model

data class RegisterApi(
    val name: String,
    val surname: String,
    val middleName: String,
    val birthday: String,
    val email: String,
    val password: String,
    val specialty: String,
)