package com.example.kotlinapplication.textClass

import androidx.databinding.Bindable
import androidx.databinding.BaseObservable
import com.example.kotlinapplication.BR

class SecondData:BaseObservable() {

    @get:Bindable
    var testOne:String = ""
    set(value) {
        field = value
        notifyPropertyChanged(BR.testOne)
    }

    @get:Bindable
    var testTwo:String =""
    set(value) {
        field = value
        notifyPropertyChanged(BR.testTwo)
    }

    @get:Bindable
    var loginName:String =""
    set(value) {
        field = value
        notifyPropertyChanged(BR.loginName)
    }

    @get:Bindable
    var loginPassword:String =""
        set(value) {
            field = value
            notifyPropertyChanged(BR.loginPassword)
        }

}