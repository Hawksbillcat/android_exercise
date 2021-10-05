package com.example.kotlinapplication.forInterface

import com.example.kotlinapplication.textClass.Posts
import com.example.kotlinapplication.textClass.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users/{user}/repos")
    fun listRepos(
        @Path("user") user: String,
        @Query("type") type: String? = null,
        @Query("sort") sort: String? = null
    ): Call<List<Repo>>
}