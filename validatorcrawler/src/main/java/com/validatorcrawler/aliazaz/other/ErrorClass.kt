package com.validatorcrawler.aliazaz.other

import android.graphics.drawable.Drawable

internal data class ErrorClass(
    var drawable: Drawable? = null,
    var padding: PaddingClass = PaddingClass(),
    var prvcolor: Int = 0,
    var id: Int = 0
)