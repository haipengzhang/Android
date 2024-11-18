package com.zhp.uibestpractice

class Message(val content: String, val type: Int) {
    // 定义常量 也就是static
    // 定义常量的关键字是const，注意只有在单例类、companion object或顶层方法中才可以使用const关键字。
    companion object {
        const val TYPE_RECEIVED = 0
        const val TYPE_SENT = 1
    }
}