package com.validator.aliazaz

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.edittextpicker.aliazaz.EditTextPicker
import com.edittextpicker.aliazaz.repository.EditTextPickerBuilder
import com.validator.aliazaz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainInterface.viewInterface {

    lateinit var bi: ActivityMainBinding
    private val presenter: MainPresenter = MainPresenter(this, MainInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bi = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bi.callback = this

        presenter.setTitle(getString(R.string.info))

        setCustomEditText()

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

    override fun setCustomEditText() {
        val txtPicker = EditTextPicker(this, EditTextPickerBuilder().apply {
            setRequired(true)
            setRangeValues(0.5f, 40.0f)
            setMask("##.##")
            setPattern("^(\\d{2,2}\\.\\d{2,2})$")
        }.build()).apply {
            hint = "##.##"
            inputType = InputType.TYPE_CLASS_NUMBER
        }

        bi.llLayout.addView(txtPicker)
    }
}