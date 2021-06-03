package com.example.coursework.user

import android.app.Application

class User(
    var id: Int,
    var name: String,
    var surname: String,
    var middleName: String,
    var birthday: String,
    var email: String,
    var specialty: String,
    var groupName: Any?,
    var courseNumber: Any?,
    var status: Int) : Application() {
    companion object {
        var userLog = false
        var user : User = User(0,
            "not found",
            "not found",
            "not found",
            "not found",
            "not found",
            "not found",
            "not found",
            "not found",
            1)
    }
}