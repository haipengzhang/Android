package com.zhp.uibestpractice

// 密封类
sealed interface Result
class Success(val msg: String): Result
class Failure(val error: Exception): Result

// 去掉else能够编译通过
// Kotlin编译器会自动检查该密封类有哪些子类，并强制要求你将每一个子类所对应的条件全部处理。
fun getResultMsg(result: Result) = when (result) {
    is Success -> result.msg
    is Failure -> "Error is ${result.error.message}"
}