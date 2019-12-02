package com.validator.aliazaz

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.validatorbox.aliazaz.Clear.Companion.clearCheckBoxes

class MainPresenter(
    private val mainActivity: MainActivity,
    private val mainInteractor: MainInteractor
) :

    MainInterface.presenterInterface {

    override fun onValidateForm(context: Context, view: View, flag: Boolean) {
        mainInteractor.crawlingLayout(context, view, flag)
    }

    override fun onShowToast(msg: String) {
        mainActivity.onShowToast(msg)
    }

    override fun onCertificationListener(view: ViewGroup, flag: Boolean) {
        clearCheckBoxes(view, flag)
    }


}