package com.example.kotlinapplication.forData

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [FavoriteUser::class], version = 1,exportSchema = false)

abstract class FavoriteUserDataBase:RoomDatabase() {

    abstract fun newFavoriteUserDao(): FavoriteUserDAO

    companion object{
        @Volatile
        private var INSTANCE:FavoriteUserDataBase? = null
        @InternalCoroutinesApi
        fun getDataBase(context: Context):FavoriteUserDataBase {
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteUserDataBase::class.java,
                    "FavoriteUser_DataBase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}