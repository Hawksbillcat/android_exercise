package com.example.kotlinapplication.forInterface

import com.example.kotlinapplication.textClass.Posts
import com.example.kotlinapplication.textClass.UserData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserDataInterFace {
    @GET("/users/{user}")
    fun info(@Path("user") user: String): Call<UserData>
}