package com.validatorcrawler.aliazaz.utils

import android.content.Context
import android.content.res.Resources
import android.view.View

fun getVisibilityFlag(view: View): Boolean {
    return view.visibility == View.GONE || !view.isEnabled || (view.tag != null && view.tag == "-1")
}

fun getString(context: Context, idName: String): String {
    val res = context.resources.getIdentifier(idName, "string", context.packageName)
    return if (res != 0) context.getString(res) else ""
}

fun getIDComponent(view: View): String {
    var idName: String? = ""
    idName = try {
        view.resources.getResourceName(view.id)?.split("id/".toRegex())
            ?.dropLastWhile { it.isEmpty() }
            ?.toTypedArray()?.get(1) + ""
    } catch (_: Resources.NotFoundException) {
        ""
    }

    return idName ?: ""
}