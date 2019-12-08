package com.validator.aliazaz

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.validatorcrawlerx.aliazaz.Clear.Companion.clearAllFields
import com.validatorcrawlerx.aliazaz.Validator.Companion.emptyCheckingContainer


class MainInteractor : MainInterface.modelInterface {

    override fun crawlingLayout(context: Context, view: View, flag: Boolean): Boolean {

        return when (emptyCheckingContainer(context, view, flag)) {

            true -> {
                clearAllFields(view)
                true
            }
            else -> false

        }

    }

    override fun keyboardHide(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}