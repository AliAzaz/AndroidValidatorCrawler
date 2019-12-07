package com.validator.aliazaz

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup

interface MainInterface {

    interface viewInterface {
        fun onShowToast(output: Boolean)
        fun onSubmitClick()
    }

    interface presenterInterface {
        fun onValidateForm(context: Context, view: View, flag: Boolean = true)
        fun onCertificationListener(view: ViewGroup, flag: Boolean)
        fun onHideKeyboard()
        fun setTitle(title: String)
    }

    interface modelInterface {
        fun crawlingLayout(context: Context, view: View, flag: Boolean): Boolean
        fun keyboardHide(activity: Activity)
    }


}