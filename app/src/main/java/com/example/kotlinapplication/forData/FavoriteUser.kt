package com.example.kotlinapplication.forData

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="FavoriteUser")
data class FavoriteUser(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val login:String,
    val avatar_url:String,
    val site_admin:Boolean,
)
