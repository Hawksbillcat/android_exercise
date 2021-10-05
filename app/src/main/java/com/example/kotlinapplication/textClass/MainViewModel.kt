package com.example.kotlinapplication.textClass

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField

class MainViewModel : ViewModel() {

        var data = ObservableField<String>("")

        init {
            data.set("init")
        }

        fun buttonClick(){
            data.set("click")
        }

}