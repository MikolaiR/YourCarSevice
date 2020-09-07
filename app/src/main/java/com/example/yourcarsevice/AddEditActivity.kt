package com.example.yourcarsevice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.yourcarsevice.databinding.ActivityAddEditBinding
import com.example.yourcarsevice.model.room.Part

const val PART_ID = "partId"
const val PART_NAME = "partName"
const val PART_UPDATE = "partUpdateDate"
const val CAR_MILLAGE = "carMillage"
const val PART_PRICE = "price"

class AddEditActivity : AppCompatActivity() {

    private val part: Part = Part()
    private lateinit var activityAddEditBinding: ActivityAddEditBinding
    private val addEditActivityClickHandlers = AddEditActivityClickHandlers()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)
        activityAddEditBinding =
            DataBindingUtil.setContentView(this@AddEditActivity, R.layout.activity_add_edit)
        activityAddEditBinding.part = part
        activityAddEditBinding.clickHandlers = addEditActivityClickHandlers

        val intent = intent
        if (intent.hasExtra(PART_ID)) {
            title = "Edit movie"
            part.partName = intent.getStringExtra(PART_NAME)
            part.partUpdateDate = intent.getStringExtra(PART_UPDATE)
            part.carMillage = intent.getStringExtra(CAR_MILLAGE)
            part.price = intent.getStringExtra(PART_PRICE)
        } else title = "Add part"
    }

    inner class AddEditActivityClickHandlers {

        fun onOkButtonClicked(view: View) {
            if (part.partName == null) {
                Toast.makeText(
                    this@AddEditActivity,
                    "Please input part name",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val intent = Intent()
                intent.putExtra(PART_NAME, part.partName)
                intent.putExtra(PART_UPDATE, part.partUpdateDate)
                intent.putExtra(CAR_MILLAGE, part.carMillage)
                intent.putExtra(PART_PRICE, part.price)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}