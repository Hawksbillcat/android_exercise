package com.example.kotlinapplication.forData

import android.database.Observable
import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface FavoriteUserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user:FavoriteUser)

    @Query("SELECT * FROM `FavoriteUser` ORDER BY login ASC")
    fun readAllData(): MutableList<FavoriteUser>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNewPost(newPost: FavoriteUser)

    @Delete
    fun deleteNewPost(newPost: FavoriteUser)
}