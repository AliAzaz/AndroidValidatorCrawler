package com.validatorbox.aliazaz

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.EditText
import com.aliazaz.validatorbox.R

internal class ValidatorError {
    companion object {

        private var error: ErrorClass? = null

        @JvmStatic
        fun putError(context: Context, viewCom: View) {

            when {
                error == null -> {
                    error = ErrorClass()
                    error!!.drawable = viewCom.background
                    error!!.id = viewCom.id
                    if (viewCom is EditText) {
                        //Get previous color
                        error!!.prvcolor = viewCom.currentTextColor
                        //Set new color
                        viewCom.setTextColor(Color.parseColor("#D8000C"))
                    }

                    viewCom.setBackgroundResource(R.drawable.image_102)
                }
            }

        }

        @JvmStatic
        fun clearError(viewCom: View) {

            when {
                error != null && error!!.id == viewCom.id -> {
                    viewCom.background = error!!.drawable
                    if (viewCom is EditText) {
                        error!!.prvcolor.let { viewCom.setTextColor(it) }
                    }
                    error = null
                }
            }

        }

    }

}