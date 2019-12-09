package com.validator.aliazaz

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.validator.aliazaz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainInterface.viewInterface {

    lateinit var bi: ActivityMainBinding
    private val presenter: MainPresenter = MainPresenter(this, MainInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bi = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bi.callback = this

        presenter.setTitle(getString(R.string.info))

        bi.f106f.setOnCheckedChangeListener { _, b ->
            if (b) presenter.onCertificationListener(bi.fldGrppocfj01, false)
            else presenter.onCertificationListener(bi.fldGrppocfj01, true)
        }

    }

    fun btnSubmit(view: View) {
        onSubmitClick()
    }

    override fun onShowToast(output: Boolean) {
        if (output) {
            Toast.makeText(this, "Form Submitted Successfully!!", Toast.LENGTH_SHORT).show()
            presenter.onHideKeyboard()
        }
    }

    override fun onSubmitClick() {
        presenter.onValidateForm(this, bi.formValidateLayout)
    }
}
