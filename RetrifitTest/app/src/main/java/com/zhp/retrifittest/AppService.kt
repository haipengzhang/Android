package com.zhp.retrifittest

import retrofit2.Call
import retrofit2.http.GET

interface AppService {

    // @GET注解，表示当调用getAppData()方法时Retrofit会发起一条GET请求，
    // 请求的地址就是我们在@GET注解中传入的具体参数
    @GET("get_json.json")
    fun getAppData(): Call<List<App>>

}