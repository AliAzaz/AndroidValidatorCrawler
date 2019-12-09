package com.validatorcrawlerx.aliazaz.other

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.validatorcrawlerx.aliazaz.R

internal object ValidatorError {

    var error: ErrorClass? = null
        private set

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

    fun clearError(activity: Activity = Activity()) {

        when {
            error != null -> {
                val view = activity.findViewById<View>(error!!.id)
                ViewCompat.setBackground(view, error!!.drawable)
                view.setPadding(
                    error!!.padding.paddingLeft,
                    error!!.padding.paddingTop,
                    error!!.padding.paddingRight,
                    error!!.padding.paddingBottom
                )
                view.clearFocus()

                when (view) {
                    is EditText -> {
                        error!!.prvcolor.let { view.setTextColor(it) }
                        view.error = null
                    }

                    is CheckBox -> {
                        view.error = null
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