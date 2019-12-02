package com.validator.aliazaz

import android.content.Context
import android.view.View
import android.view.ViewGroup

interface MainInterface {

    interface viewInterface {

        fun onShowToast(msg: String)
        fun onSubmitClick()
    }

    interface presenterInterface {
        fun onValidateForm(context: Context, view: View, flag: Boolean)
        fun onShowToast(msg: String)
        fun onCertificationListener(view: ViewGroup, flag: Boolean)
    }

    interface modelInterface {
        fun crawlingLayout(context: Context, view: View, flag: Boolean)
    }


}