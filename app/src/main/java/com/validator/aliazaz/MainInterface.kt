package com.validator.aliazaz

import android.content.Context
import android.view.View
import android.view.ViewGroup

interface MainInterface {

    interface viewInterface {
        fun onShowToast(putput: Boolean)
        fun onSubmitClick()
    }

    interface presenterInterface {
        fun onValidateForm(context: Context, view: View, flag: Boolean = true)
        fun onCertificationListener(view: ViewGroup, flag: Boolean)
    }

    interface modelInterface {
        fun crawlingLayout(context: Context, view: View, flag: Boolean): Boolean
    }


}