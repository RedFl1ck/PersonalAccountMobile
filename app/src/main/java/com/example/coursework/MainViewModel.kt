package com.example.coursework

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework.model.UserApi
import com.example.coursework.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse : MutableLiveData<retrofit2.Response<UserApi>> = MutableLiveData()

    fun auth(userApi: UserApi){
        viewModelScope.launch {
            val response = repository.auth(userApi)
            myResponse.value = response
        }
    }
}