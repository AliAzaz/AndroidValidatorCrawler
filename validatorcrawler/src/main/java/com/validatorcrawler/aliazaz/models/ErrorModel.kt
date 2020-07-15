package com.validatorcrawler.aliazaz.models

import android.graphics.drawable.Drawable

internal data class ErrorModel(
    var activityName: String,
    var drawable: Drawable? = null,
    var padding: PaddingModel = PaddingModel(),
    var prvcolor: Int = 0,
    var id: Int = 0
)