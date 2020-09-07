package com.example.yourcarsevice.model.room

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.yourcarsevice.BR

@Entity(tableName = "parts_table")
class Part : BaseObservable() {
    @PrimaryKey(autoGenerate = true)
    var partId: Int? = null
        @Bindable
        get
        set(value) {
            field = value
            notifyPropertyChanged(BR.partId)
        }
    @ColumnInfo(name = "part_name")
    var partName: String? = null
        @Bindable
        get
        set(value) {
            field = value
            notifyPropertyChanged(BR.partName)
        }
    @ColumnInfo(name = "part_update_date")
    var partUpdateDate: String? = null
        @Bindable
        get
        set(value) {
            field = value
            notifyPropertyChanged(BR.partUpdateDate)
        }
    @ColumnInfo(name = "car_millage")
    var carMillage: String? = null
        @Bindable
        get
        set(value) {
            field = value
            notifyPropertyChanged(BR.carMillage)
        }
    @ColumnInfo(name = "comment")
    var comment: String? = null
        @Bindable
        get
        set(value) {
            field = value
            notifyPropertyChanged(BR.comment)
        }
    @ColumnInfo(name = "price")
    var price: String? = null
        @Bindable
        get
        set(value) {
            field = value
            notifyPropertyChanged(BR.price)
        }
}
