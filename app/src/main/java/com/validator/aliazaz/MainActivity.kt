package com.validator.aliazaz

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.validator.aliazaz.databinding.ActivityMainBinding
import com.validatorbox.aliazaz.Clear.Companion.clearAllFields
import com.validatorbox.aliazaz.Validator.Companion.emptyCheckingContainer

class MainActivity : AppCompatActivity(), MainInterface.viewInterface {

    private val bi: ActivityMainBinding =
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    private val presenter: MainPresenter = MainPresenter(this, MainInteractor())

    init {
        bi.callback = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bi.f106e.setOnCheckedChangeListener { _, b ->
            if (b) presenter.onCertificationListener(bi.fldGrppocfj01, false)
            else presenter.onCertificationListener(bi.fldGrppocfj01, true)
        }

    }

    fun btnSubmit(view: View) {
        if (!emptyCheckingContainer(this, bi.formValidateLayout)) return
        clearAllFields(bi.formValidateLayout)
    }

    override fun onShowToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSubmitClick() {



    }
}
