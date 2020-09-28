package com.example.yourcarsevice.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.yourcarsevice.BR


class Statistic:BaseObservable() {
     var totalPrice: Double? = null
         @Bindable
         get
         set(value) {
             field = value
             notifyPropertyChanged(BR.totalPrice)
         }
    var totalMillage: Int? = null
        @Bindable
        get
        set(value) {
            field = value
            notifyPropertyChanged(BR.totalMillage)
        }
    var costPerKilometer: Double? = null
        @Bindable
        get
        set(value) {
            field = value
            notifyPropertyChanged(BR.costPerKilometer)
        }
 }


