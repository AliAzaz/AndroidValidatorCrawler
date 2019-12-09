package com.validatorcrawler.aliazaz

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.edittextpicker.aliazaz.EditTextPicker
import com.validatorcrawler.aliazaz.other.ValidatorError

class Validator {

    companion object {

        @JvmOverloads
        @JvmStatic
        fun emptyTextBox(context: Context, txt: EditText, toggleFlag: Boolean = true): Boolean {
            return when {
                txt is EditTextPicker -> return emptyEditTextPicker(context, txt, toggleFlag)
                TextUtils.isEmpty(txt.text.toString()) -> {
                    ValidatorError.putError(context, txt)
                    if (toggleFlag) {
                        Toast.makeText(
                            context,
                            "ERROR(Empty) ${getString(context, getIDComponent(txt))}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    txt.error = "Required"    // Set Error on last radio button
                    txt.requestFocus()
                    Log.i(
                        context.javaClass.name,
                        "${context.resources.getResourceEntryName(txt.id)} : Required"
                    )
                    false
                }
                else -> true
            }
        }

        @JvmOverloads
        @JvmStatic
        fun emptyTextView(context: Context, txt: TextView, toggleFlag: Boolean = true): Boolean {
            return when {
                TextUtils.isEmpty(txt.text.toString()) -> {
                    ValidatorError.putError(context, txt)
                    if (toggleFlag) {
                        Toast.makeText(
                            context,
                            "ERROR(Empty) ${getString(context, getIDComponent(txt))}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    txt.error = "Required"    // Set Error on last radio button
                    txt.requestFocus()
                    Log.i(
                        context.javaClass.name,
                        "${context.resources.getResourceEntryName(txt.id)} : Required"
                    )
                    false
                }
                else -> true
            }
        }

        @JvmOverloads
        @JvmStatic
        fun emptyCustomTextBox(
            context: Context,
            txt: TextView,
            msg: String,
            toggleFlag: Boolean = true
        ): Boolean {
            ValidatorError.putError(context, txt)
            if (toggleFlag)
                Toast.makeText(context, "ERROR: $msg", Toast.LENGTH_SHORT).show()
            txt.error = msg
            txt.isFocusableInTouchMode = true
            txt.requestFocus()
            Log.i(
                context.javaClass.name,
                context.resources.getResourceEntryName(txt.id) + ": " + msg
            )
            return false
        }

        @JvmOverloads
        @JvmStatic
        fun emptyEditTextPicker(
            context: Context,
            txt: EditText,
            toggleFlag: Boolean = true
        ): Boolean {
            var messageConv = ""
            var flag = true
            if (!(txt as EditTextPicker).isEmptyTextBox) {
                flag = false
                messageConv = "ERROR(Empty)"
            } else if (!txt.isRangeTextValidate) {
                flag = false
                messageConv = "ERROR(Range)"
            } else if (!txt.isTextEqualToPattern) {
                flag = false
                messageConv = "ERROR(Pattern)"
            }

            return if (!flag) {
                ValidatorError.putError(context, txt)
                if (toggleFlag) {
                    Toast.makeText(
                        context,
                        "$messageConv: ${getString(context, getIDComponent(txt))}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Log.i(
                    context.javaClass.name,
                    "${context.resources.getResourceEntryName(txt.id)} : $messageConv"
                )
                false
            } else true
        }

        @JvmOverloads
        @JvmStatic
        fun rangeTextBox(
            context: Context,
            txt: EditText,
            min: Int,
            max: Int,
            type: String,
            toggleFlag: Boolean = true
        ): Boolean {

            return if (Integer.valueOf(txt.text.toString()) < min || Integer.valueOf(txt.text.toString()) > max) {
                ValidatorError.putError(context, txt)
                if (toggleFlag) {
                    Toast.makeText(
                        context,
                        "ERROR(Invalid) ${getString(context, getIDComponent(txt))}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                txt.error = "Range is $min to $max ($type)"    // Set Error on last radio button
                txt.requestFocus()
                Log.i(
                    context.javaClass.name,
                    "${context.resources.getResourceEntryName(txt.id)} : Range is $min to $max times"
                )
                false
            } else true

        }

        @JvmOverloads
        @JvmStatic
        fun rangeTextBox(
            context: Context,
            txt: EditText,
            min: Double,
            max: Double,
            type: String,
            toggleFlag: Boolean = true
        ): Boolean {

            return if (java.lang.Double.valueOf(txt.text.toString()) < min || java.lang.Double.valueOf(
                    txt.text.toString()
                ) > max
            ) {
                ValidatorError.putError(context, txt)
                if (toggleFlag) {
                    Toast.makeText(
                        context,
                        "ERROR(Invalid) ${getString(context, getIDComponent(txt))}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                txt.error =
                    "Range is $min to $max (${type.let { "for $it" }})"    // Set Error on last radio button
                txt.requestFocus()
                Log.i(
                    context.javaClass.name,
                    "${context.resources.getResourceEntryName(txt.id)} : Range is $min to $max times"
                )
                false
            } else true

        }

        @JvmOverloads
        @JvmStatic
        fun emptySpinner(context: Context, spin: Spinner, toggleFlag: Boolean = true): Boolean {
            return when {
                (spin.selectedItemPosition == 0) -> {
                    ValidatorError.putError(context, spin)
                    if (toggleFlag) {
                        Toast.makeText(
                            context,
                            "ERROR(Empty) ${getString(context, getIDComponent(spin))}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    (spin.selectedView as TextView).text = "Required"
                    (spin.selectedView as TextView).setTextColor(Color.RED)
                    spin.isFocusableInTouchMode = true
                    spin.requestFocus()
                    Log.i(
                        context.javaClass.name,
                        "${context.resources.getResourceEntryName(spin.id)} : Required"
                    )
                    false
                }
                else -> true
            }
        }

        @JvmOverloads
        @JvmStatic
        fun emptyRadioButton(
            context: Context,
            rdGrp: RadioGroup,
            rdBtn: RadioButton,
            toggleFlag: Boolean = true
        ): Boolean {
            if (rdGrp.checkedRadioButtonId == -1) {
                ValidatorError.putError(context, rdGrp)
                if (toggleFlag) {
                    Toast.makeText(
                        context,
                        "ERROR(Empty) ${getString(context, getIDComponent(rdGrp))}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                rdBtn.error = "Required"    // Set Error on last radio button
                rdBtn.isFocusable = true
                rdBtn.isFocusableInTouchMode = true
                rdBtn.requestFocus()
                Log.i(
                    context.javaClass.name,
                    "${context.resources.getResourceEntryName(rdGrp.id)} : Required"
                )
                return false
            } else {
                var rdbFlag: Boolean
                val checkedRadio =
                    (context as Activity).findViewById<RadioButton>(rdGrp.checkedRadioButtonId)
                val i = rdGrp.indexOfChild(checkedRadio)
                rdbFlag =
                    if (i + 1 == rdGrp.childCount || getIDComponent(checkedRadio) != rdGrp.getChildAt(
                            i + 1
                        ).tag
                    )
                        true
                    else {
                        val v = rdGrp.getChildAt(i + 1)
                        emptyCheckingContainer(context, v, toggleFlag)
                    }

                return if (rdbFlag) {
                    rdBtn.error = null
                    rdBtn.clearFocus()
                    true
                } else
                    false

            }
        }

        @JvmOverloads
        @JvmStatic
        fun emptyCheckBox(
            context: Context,
            cbx: CheckBox,
            toggleFlag: Boolean = true
        ): Boolean {
            return when {
                !cbx.isChecked -> {
                    ValidatorError.putError(context, cbx)
                    cbx.error = getString(context, getIDComponent(cbx))
                    if (toggleFlag)
                        Toast.makeText(
                            context,
                            "ERROR(Empty) ${getString(context, getIDComponent(cbx))}",
                            Toast.LENGTH_SHORT
                        ).show()
                    return false
                }
                else -> true
            }

        }

        @JvmOverloads
        @JvmStatic
        fun emptyMultiCheckBox(
            context: Context,
            container: ViewGroup,
            toggleFlag: Boolean = true
        ): Boolean {
            var flag = false
            var subflag = true
            for (i in 0 until container.childCount) {
                val v = container.getChildAt(i)
                if (v is ViewGroup)
                    if (emptyMultiCheckBox02(context, v)) {
                        flag = true
                    } else {
                        if (ValidatorError.error == null) continue
                        val view =
                            (context as Activity).findViewById<View>(ValidatorError.error!!.id)
                        if (view !is CheckBox) {
                            flag = false
                            break
                        }
                    }
                if (v is CheckBox) {
                    v.error = null

                    if (!v.isEnabled) {
                        flag = true
                        continue
                    } else {
                        if (!flag)
                            flag = false
                    }

                    if (v.isChecked) {
                        flag = true
                        if (i + 1 == container.childCount || getIDComponent(v) != container.getChildAt(
                                i + 1
                            ).tag
                        )
                            continue
                        subflag =
                            emptyCheckingContainer(context, container.getChildAt(i + 1), toggleFlag)
                        if (!subflag) break
                    }
                }
            }
            if (!flag) {
                ValidatorError.putError(context, container)
                if (toggleFlag)
                    Toast.makeText(
                        context,
                        "ERROR(Empty)",
                        Toast.LENGTH_SHORT
                    ).show()
                container.isFocusable = true
                container.isFocusableInTouchMode = true
                container.requestFocus()
                Log.i(
                    context.javaClass.name,
                    " ${getIDComponent(container)} :  Required"
                )
                return false
            } else if (!subflag) return false

            container.clearFocus()
            return true
        }

        @JvmOverloads
        @JvmStatic
        fun emptyCheckingContainer(
            context: Context,
            view: View,
            toggleFlag: Boolean = true
        ): Boolean {

            ValidatorError.clearError(context as Activity)

            if (view.visibility == View.GONE || !view.isEnabled || (view.tag != null && view.tag == "-1")) return true

            when (view) {
                is RadioGroup -> {
                    var radioFlag = false
                    lateinit var v: RadioButton
                    for (j in 0 until view.childCount) {
                        if (view.getChildAt(j) is RadioButton) {
                            v = view.getChildAt(j) as RadioButton
                            radioFlag = true
                            break
                        }
                    }

                    if (!radioFlag) return true

                    if (!emptyRadioButton(context, view, v, toggleFlag)) return false

                }

                is Spinner -> if (!emptySpinner(context, view, toggleFlag)) return false

                is EditText -> if (!emptyTextBox(context, view, toggleFlag)) return false

                is CheckBox -> if (!emptyCheckBox(context, view, toggleFlag)) return false

                is ViewGroup -> when (view.tag) {
                    "0" -> if (!emptyMultiCheckBox(context, view, toggleFlag)) {
                        return false
                    }
                    else -> {

                        loop@ for (i in 0 until view.childCount) {
                            when (emptyCheckingContainer(context, view.getChildAt(i), toggleFlag)) {
                                true -> continue@loop
                                false -> return false
                            }
                        }

                    }
                }

            }

            return true
        }

        private fun emptyMultiCheckBox02(
            context: Context,
            container: ViewGroup
        ): Boolean {
            var flag = false
            var subflag = true
            for (i in 0 until container.childCount) {
                val v = container.getChildAt(i)
                if (v is ViewGroup)
                    if (emptyMultiCheckBox02(context, v)) {
                        flag = true
                    } else {
                        if (ValidatorError.error == null) continue
                        val view =
                            (context as Activity).findViewById<View>(ValidatorError.error!!.id)
                        if (view !is CheckBox) {
                            flag = false
                            break
                        }
                    }
                if (v is CheckBox) {
                    v.error = null

                    if (!v.isEnabled) {
                        flag = true
                        continue
                    } else {
                        if (!flag)
                            flag = false
                    }

                    if (v.isChecked) {
                        flag = true
                        if (i + 1 == container.childCount || getIDComponent(v) != container.getChildAt(
                                i + 1
                            ).tag
                        )
                            continue
                        subflag =
                            emptyCheckingContainer(context, container.getChildAt(i + 1), false)
                        if (!subflag) break
                    }
                }
            }
            if (!flag) return false
            else if (!subflag) return false
            return true
        }

        private fun getIDComponent(view: View): String {
            val idName = view.resources.getResourceName(view.id)?.split("id/".toRegex())
                ?.dropLastWhile { it.isEmpty() }
                ?.toTypedArray()
            return idName?.get(1) + ""
        }

        private fun getString(context: Context, idName: String): String {
            val res = context.resources.getIdentifier(idName, "string", context.packageName)
            return if (res != 0) context.getString(res) else ""
        }

    }
}