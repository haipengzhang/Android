package com.example.jetpacktest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Repository {

    fun getUser(userId: String): LiveData<User> {
        val liveData = MutableLiveData<User>()
        liveData.value = User(userId, userId, 0)
        return liveData
    }

    fun refresh(): LiveData<String> {
        val liveData = MutableLiveData<String>()
        liveData.value = (0..10000).random().toString()
        return liveData
    }

}