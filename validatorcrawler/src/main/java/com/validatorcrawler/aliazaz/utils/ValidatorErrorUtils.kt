package com.validatorcrawler.aliazaz.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.validatorcrawler.aliazaz.BuildConfig
import com.validatorcrawler.aliazaz.R
import com.validatorcrawler.aliazaz.models.ErrorModel
import com.validatorcrawler.aliazaz.models.PaddingModel

internal object ValidatorErrorUtils {

    var error: ErrorModel? = null
        private set

    @SuppressLint("NewApi")
    fun putError(context: Context, targetView: View) {
        /*
        * If target view id is not defined then error will not set
        * */
        if (targetView.id == -1) {
            Log.e(
                BuildConfig.LIBRARY_PACKAGE_NAME.toString(),
                context.resources.getString(R.string.resource_id_na)
            )
            return
        }

        /*
        * Clear the previous set error then move to put error on a new place
        * */
        clearError(context as Activity)
        /*
        * After setting the error null or if putting error first time.
        * */
        if (error == null) {
            error = ErrorModel(
                context::class.java.name,
                targetView.background,
                padding = PaddingModel(
                    targetView.paddingTop,
                    targetView.paddingStart,
                    targetView.paddingRight,
                    targetView.paddingBottom,
                    targetView.paddingEnd,
                    targetView.paddingLeft
                ),
                id = targetView.id
            )
            if (targetView is EditText) {
                //Get previous color
                error!!.prvcolor = targetView.currentTextColor
                //Set new color
                targetView.setTextColor(Color.parseColor("#D8000C"))
            }
            targetView.background = ContextCompat.getDrawable(context, R.drawable.image_103)
        }
    }

    fun clearError(activity: Activity) {
        error?.let {
            when (it.activityName) {
                activity::class.java.name -> {
                    val resolvedView: View = activity.findViewById(it.id)
                    ViewCompat.setBackground(resolvedView, it.drawable)
                    resolvedView.setPadding(
                        it.padding.paddingLeft,
                        it.padding.paddingTop,
                        it.padding.paddingRight,
                        it.padding.paddingBottom
                    )
                    resolvedView.clearFocus()

                    when (resolvedView) {
                        is EditText -> {
                            it.prvcolor.let { resolvedView.setTextColor(it) }
                            resolvedView.error = null
                        }

                        is CheckBox -> resolvedView.error = null
                        is Spinner -> (resolvedView.selectedView as TextView).error = null
                    }
                    error = null
                }

                else -> error = null
            }
        }
    }

}