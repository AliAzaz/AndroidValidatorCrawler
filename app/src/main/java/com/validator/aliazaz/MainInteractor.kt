package com.validator.aliazaz

import android.content.Context
import android.view.View
import com.validatorbox.aliazaz.Clear.Companion.clearAllFields
import com.validatorbox.aliazaz.Validator.Companion.emptyCheckingContainer

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
}