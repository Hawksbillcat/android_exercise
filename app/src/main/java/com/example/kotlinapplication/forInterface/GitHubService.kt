package com.example.kotlinapplication.forInterface

import com.example.kotlinapplication.textClass.GithubData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {
    @GET("users")
    fun listUsers():Call<List<GithubData>>
}