package com.example.jetpacktest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap

// jetpack的viewmodel组件
class MainViewModel(countReserved: Int) : ViewModel() {

    // jetpack的livedata组件
    // postValue()方法用于在非主线程中给LiveData设置数据。而上述代码其实就是调用getValue()和setValue()方法
    val counter: LiveData<Int>
        get() = _counter

    private var _counter = MutableLiveData<Int>()

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        val count = _counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }

    /*
    * 首先，当外部调用MainViewModel的getUser()方法来获取用户数据时，并不会发起任何请求或者函数调用，只会将传入的userId值设置到userIdLiveData当中。
    * 一旦userIdLiveData的数据发生变化，那么观察userIdLiveData的switchMap()方法就会执行，并且调用我们编写的转换函数。
    * 然后在转换函数中调用Repository.getUser()方法获取真正的用户数据。
    * 同时，switchMap()方法会将Repository.getUser()方法返回的LiveData对象转换成一个可观察的LiveData对象，
    * 对于Activity而言，只要去观察这个LiveData对象就可以了。
    * */
    private val userIdLiveData = MutableLiveData<String>()
    /* map方法屏蔽别的变量
    val userName: LiveData<String> = Transformations.map(userLiveData) { user ->
        "${user.firstName} ${user.lastName}"
    }
    */
    // Transformations在新版本过期，直接用livedata上执行switchmap
    val user: LiveData<User> = userIdLiveData.switchMap { userId ->
        Repository.getUser(userId)
    }

    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }


    // 空方法触发刷新
    private val refreshLiveData = MutableLiveData<Any?>()

    val refreshResult = refreshLiveData.switchMap() {
        Repository.refresh()  // 假设Repository中已经定义了refresh()方法
    }
}