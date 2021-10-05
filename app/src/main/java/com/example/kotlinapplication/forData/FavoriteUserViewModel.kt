package com.example.kotlinapplication.forData

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class FavoriteUserViewModel(application:Application):AndroidViewModel(application) {

    private val readAllData:MutableList<FavoriteUser>
    private val repository:FavoriteUserRepository

    init {
        val favoriteUserDAO = FavoriteUserDataBase.getDataBase(application).newFavoriteUserDao()
        repository = FavoriteUserRepository(favoriteUserDAO)
        readAllData = repository.readAllData
    }

    fun addUser(favoriteUser:FavoriteUser){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(favoriteUser)
        }
    }
}


