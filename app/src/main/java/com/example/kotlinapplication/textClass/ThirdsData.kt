package com.example.kotlinapplication.textClass

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ThirdsData: ViewModel() {

    var num = 0

    val curNum:MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val curBoolean:MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

}