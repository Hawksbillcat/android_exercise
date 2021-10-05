package com.example.kotlinapplication.textClass

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

data class UserData(
    @SerializedName("avatar_url")
    val avatar_url:String,
    @SerializedName("login")
    val login: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("bio")
    val bio: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("blog")
    val blog: String,
    @SerializedName("site_admin")
    val site_admin:Boolean
)
