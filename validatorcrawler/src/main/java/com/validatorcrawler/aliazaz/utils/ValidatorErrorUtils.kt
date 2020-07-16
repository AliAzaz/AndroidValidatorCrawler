package com.validatorcrawler.aliazaz.utils

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
import com.validatorcrawler.aliazaz.R
import com.validatorcrawler.aliazaz.models.ErrorModel
import com.validatorcrawler.aliazaz.models.PaddingModel

internal object ValidatorErrorUtils {

    var error: ErrorModel? = null
        private set

    @SuppressLint("NewApi")
    fun putError(context: Context, viewCom: View) {

        clearError(context as Activity)

        when (error) {
            null -> {
                error = ErrorModel(
                    context::class.java.name,
                    viewCom.background,
                    padding = PaddingModel(
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
            error != null && error!!.activityName == activity::class.java.name -> {
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
            error != null && error!!.activityName != activity::class.java.name -> error = null
        }

    }

}