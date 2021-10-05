package com.example.kotlinapplication.forData

import androidx.lifecycle.LiveData

class FavoriteUserRepository(private val favoriteUserDAO: FavoriteUserDAO) {

    val readAllData: MutableList<FavoriteUser> = favoriteUserDAO.readAllData()

    fun addUser(favoriteUser:FavoriteUser){
        favoriteUserDAO.addUser(favoriteUser)
    }
}