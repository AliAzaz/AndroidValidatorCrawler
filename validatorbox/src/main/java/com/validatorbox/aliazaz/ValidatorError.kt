package com.validatorbox.aliazaz

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.aliazaz.validatorbox.R
import com.validatorbox.aliazaz.other.ErrorClass
import com.validatorbox.aliazaz.other.PaddingClass

internal object ValidatorError {

    private var error: ErrorClass? = null

    @SuppressLint("NewApi")
    fun putError(context: Context, viewCom: View) {

        when (error) {
            null -> {
                error = ErrorClass(
                    viewCom.background,
                    padding = PaddingClass(
                        viewCom.paddingTop,
                        viewCom.paddingStart,
                        viewCom.paddingRight,
                        viewCom.paddingBottom,
                        viewCom.paddingEnd,
                        viewCom.paddingLeft
                    ),
                    id = viewCom.id
                )
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

    fun clearError(viewCom: View, activity: Activity = Activity()) {

        when {
            /*error != null && error!!.id == viewCom.id -> {
                ViewCompat.setBackground(viewCom, error!!.drawable)
                viewCom.setPadding(
                    error!!.padding.paddingLeft,
                    error!!.padding.paddingTop,
                    error!!.padding.paddingRight,
                    error!!.padding.paddingBottom
                )
                if (viewCom is EditText) {
                    error!!.prvcolor.let { viewCom.setTextColor(it) }
                }
                error = null
            }*/
            error != null -> {
                val view = activity.findViewById<View>(error!!.id)
                ViewCompat.setBackground(view, error!!.drawable)
                view.setPadding(
                    error!!.padding.paddingLeft,
                    error!!.padding.paddingTop,
                    error!!.padding.paddingRight,
                    error!!.padding.paddingBottom
                )

                when (view) {
                    is EditText -> {
                        error!!.prvcolor.let { view.setTextColor(it) }
                        view.error = null
                        view.clearFocus()
                    }

                    is CheckBox -> {
                        view.error = null
                        view.clearFocus()
                    }

                    is Spinner -> {
                        (view.selectedView as TextView).error = null
                    }
                }


                error = null
            }
        }

    }

}