package com.zhp.networktest

interface HttpCallbackListener {
    fun onFinish(response: String)
    fun onError(e: Exception)
}