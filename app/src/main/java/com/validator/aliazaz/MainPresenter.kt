package com.validator.aliazaz

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.validatorcrawlerx.aliazaz.Clear.Companion.clearCheckBoxes

class MainPresenter(
    private val mainActivity: MainActivity,
    private val mainInteractor: MainInteractor
) :

    MainInterface.presenterInterface {

    override fun onValidateForm(context: Context, view: View, flag: Boolean) {
        val output = mainInteractor.crawlingLayout(context, view, flag)
        mainActivity.onShowToast(output)
    }

    override fun onCertificationListener(view: ViewGroup, flag: Boolean) {
        clearCheckBoxes(view, flag)
    }

    override fun onHideKeyboard() {
        mainInteractor.keyboardHide(mainActivity)
    }

    override fun setTitle(title: String) {
        mainActivity.title = title
    }
}