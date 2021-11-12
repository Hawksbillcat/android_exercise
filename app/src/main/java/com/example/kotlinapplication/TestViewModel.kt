package com.example.kotlinapplication

import android.content.ContentValues
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestViewModel :ViewModel() {

    private val sec = MutableLiveData<Int>()
    private val bmp = MutableLiveData<Bitmap>()

    fun getSec():LiveData<Int>{
        Log.i(ContentValues.TAG, "sec 是多少喇 $sec")
        return sec
    }

    fun setSec(i:Int){
        // (Math.random()*50).toInt()
        sec.value = i
    }

    fun setB(b:Bitmap){
        bmp.value = b
    }

    fun getB():LiveData<Bitmap>{
        Log.i(ContentValues.TAG, "bbb clicked $bmp")
        return bmp
    }



}