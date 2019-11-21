package com.validatorbox.aliazaz

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.aliazaz.validatorbox.R

internal object ValidatorError {

    private var error: ErrorClass? = null

    fun putError(context: Context, viewCom: View) {

        when (error) {
            null -> {
                error = ErrorClass(viewCom.background, id = viewCom.id)
                if (viewCom is EditText) {
                    //Get previous color
                    error!!.prvcolor = viewCom.currentTextColor
                    //Set new color
                    viewCom.setTextColor(Color.parseColor("#D8000C"))
                }
                viewCom.background = ContextCompat.getDrawable(context, R.drawable.image_102)
            }
        }

    }

    fun clearError(viewCom: View) {

        when {
            error != null && error!!.id == viewCom.id -> {
                ViewCompat.setBackground(viewCom, error!!.drawable)
                if (viewCom is EditText) {
                    error!!.prvcolor.let { viewCom.setTextColor(it) }
                }
                error = null
            }
        }

    }

}