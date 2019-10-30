package com.validator.aliazaz

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.validator.aliazaz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var bi: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bi.callback = this
    }

    fun btnSubmit(view: View) {
        com.validatorbox.aliazaz.Validator.emptyCheckingContainer(this, bi.formValidateLayout)
    }
}
