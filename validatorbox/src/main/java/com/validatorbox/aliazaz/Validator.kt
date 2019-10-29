package com.validatorbox.aliazaz

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import com.edittextpicker.aliazaz.EditTextPicker

class Validator {

    companion object {

        @JvmStatic
        fun emptyTextBox(context: Context, txt: EditText, toggleFlag: Boolean = true): Boolean {
            return if (TextUtils.isEmpty(txt.text.toString())) {
                ValidatorError.putError(context, txt)
                if (toggleFlag) {
                    Toast.makeText(
                        context,
                        "ERROR(Empty): ${getString(context, getIDComponent(txt))}",
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
            } else {
                ValidatorError.clearError(txt)
                txt.error = null
                txt.clearFocus()
                true
            }
        }

        @JvmStatic
        fun emptyTextView(context: Context, txt: TextView, toggleFlag: Boolean = true): Boolean {
            return if (TextUtils.isEmpty(txt.text.toString())) {
                ValidatorError.putError(context, txt)
                if (toggleFlag) {
                    Toast.makeText(
                        context,
                        "ERROR(Empty): ${getString(context, getIDComponent(txt))}",
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
            } else {
                ValidatorError.clearError(txt)
                txt.error = null
                txt.clearFocus()
                true
            }
        }

        @JvmStatic
        fun emptyCustomTextBox(
            context: Context,
            txt: TextView,
            msg: String,
            toggleFlag: Boolean = true
        ): Boolean {
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
            } else {
                ValidatorError.clearError(txt)
                txt.setError(null)
                txt.clearFocus()
                true
            }
        }

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
                        "ERROR(Invalid): ${getString(context, getIDComponent(txt))}",
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
            } else {
                ValidatorError.clearError(txt)
                txt.error = null
                txt.clearFocus()
                true
            }
        }

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
                        "ERROR(Invalid): ${getString(context, getIDComponent(txt))}",
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
            } else {
                ValidatorError.clearError(txt)
                txt.error = null
                txt.clearFocus()
                true
            }
        }

        @JvmStatic
        fun emptySpinner(context: Context, spin: Spinner, toggleFlag: Boolean = true): Boolean {
            return if (spin.selectedItemPosition == 0) {
                ValidatorError.putError(context, spin)
                if (toggleFlag) {
                    Toast.makeText(
                        context,
                        "ERROR(Empty): ${getString(context, getIDComponent(spin))}",
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
            } else {
                ValidatorError.clearError(spin)
                (spin.selectedView as TextView).error = null
                true
            }
        }

        @JvmStatic
        fun emptyRadioButton(
            context: Context,
            rdGrp: RadioGroup,
            rdBtn: RadioButton,
            toggleFlag: Boolean = true
        ): Boolean {
            if (rdGrp.checkedRadioButtonId == -1) {
                ValidatorError.putError(context, rdBtn)
                if (toggleFlag) {
                    Toast.makeText(
                        context,
                        "ERROR(Empty): ${getString(context, getIDComponent(rdGrp))}",
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
                var rdbFlag = true
                for (j in 0 until rdGrp.childCount) {
                    val innerV = rdGrp.getChildAt(j)

                    if (innerV is RadioButton) {
                        if (!innerV.isChecked) continue
                    }

                    if (innerV is EditText) {
                        if (getIDComponent(rdGrp.findViewById(rdGrp.checkedRadioButtonId)) == innerV.getTag())
                            rdbFlag = if (innerV is EditTextPicker)
                                emptyEditTextPicker(
                                    context,
                                    innerV as EditText,
                                    toggleFlag
                                )
                            else
                                emptyTextBox(
                                    context,
                                    innerV,
                                    toggleFlag
                                )
                    }
                    if (!rdbFlag) break
                }

                return if (rdbFlag) {
                    ValidatorError.clearError(rdBtn)
                    rdBtn.error = null
                    rdBtn.clearFocus()
                    rdbFlag
                } else
                    rdbFlag

            }
        }

        @JvmStatic
        fun emptyCheckBox(
            context: Context,
            cbx: CheckBox,
            toggleFlag: Boolean = true
        ): Boolean {
            return if (!cbx.isChecked) {
                ValidatorError.putError(context, cbx)
                cbx.error = getString(context, getIDComponent(cbx))
                if (toggleFlag)
                    Toast.makeText(
                        context,
                        "ERROR(Empty): ${getString(context, getIDComponent(cbx))}",
                        Toast.LENGTH_SHORT
                    ).show()
                return false
            } else {
                ValidatorError.clearError(cbx)
                cbx.error = null
                cbx.clearFocus()
                true
            }
        }

        @JvmStatic
        fun emptyCheckBox(
            context: Context,
            container: LinearLayout,
            cbx: CheckBox,
            toggleFlag: Boolean = true
        ): Boolean {
            var flag = false
            for (i in 0 until container.childCount) {
                val v = container.getChildAt(i)
                if (v is CheckBox) {
                    v.error = null
                    ValidatorError.clearError(cbx)

                    if (!v.isEnabled) {
                        flag = true
                        continue
                    } else {
                        if (!flag)
                            flag = false
                    }

                    if (v.isChecked) {
                        flag = true

                        for (j in 1 until container.childCount) {
                            val innerV = container.getChildAt(j)
                            if (innerV is EditText) {
                                if (getIDComponent(v) == innerV.getTag()) {
                                    flag = if (innerV is EditTextPicker)
                                        emptyEditTextPicker(
                                            context,
                                            innerV as EditText,
                                            flag
                                        )
                                    else
                                        emptyTextBox(
                                            context,
                                            innerV,
                                            flag
                                        )
                                }
                            }
                            if (!flag) break
                        }
                        if (!flag) break
                    }
                }
            }
            if (!flag) {
                ValidatorError.putError(context, cbx)
                if (toggleFlag)
                    Toast.makeText(
                        context,
                        "ERROR(Empty): ${getString(context, getIDComponent(cbx))}",
                        Toast.LENGTH_SHORT
                    ).show()
                cbx.error = "Required"    // Set Error on last radio button
                Log.i(
                    context.javaClass.name,
                    "${context.resources.getResourceEntryName(cbx.id)} :  Required"
                )
                return false
            }
            return true
        }

        @JvmStatic
        fun emptyCheckingContainer(
            context: Context,
            lv: ViewGroup,
            toggleFlag: Boolean = true
        ): Boolean {

            for (i in 0 until lv.childCount) {
                val view = lv.getChildAt(i)

                if (view.visibility == View.GONE || !view.isEnabled)
                    continue

                // use tag for situation when you don't want to read the component
                if (view.tag != null && view.tag == "-1")
                    continue

                if (view is CardView) {
                    if (!emptyCheckingContainer(context, view)) {
                        return false
                    }
                } else if (view is RadioGroup) {

                    var radioFlag = false
                    var v: View? = null
                    for (j in 0 until view.childCount) {
                        if (view.getChildAt(j) is RadioButton) {
                            v = view.getChildAt(j)
                            radioFlag = true
                            break
                        }
                    }

                    if (!radioFlag) continue

                    if (v != null) {

                        if (!emptyRadioButton(context, view, v as RadioButton, toggleFlag)) {
                            return false
                        }
                    }

                } else if (view is Spinner) {
                    if (!emptySpinner(context, view, toggleFlag)) {
                        return false
                    }
                } else if (view is EditText) {
                    if (view is EditTextPicker) {
                        if (!emptyEditTextPicker(
                                context,
                                view as EditText,
                                toggleFlag
                            )
                        )
                            return false
                    } else {
                        if (!emptyTextBox(
                                context,
                                view,
                                toggleFlag
                            )
                        ) {
                            return false
                        }
                    }
                } else if (view is CheckBox) {
                    if (!emptyCheckBox(context, view, toggleFlag))
                        return false
                } else if (view is LinearLayout) {
                    if (view.getTag() != null && view.getTag() == "0") {
                        if (!emptyCheckBox(
                                context, view,
                                view.getChildAt(0) as CheckBox, toggleFlag
                            )
                        ) {
                            return false
                        }
                    } else {
                        if (!emptyCheckingContainer(context, view)) {
                            return false
                        }
                    }
                } else if (view is ViewGroup) {
                    if (!emptyCheckingContainer(context, view)) {
                        return false
                    }
                }

            }
            return true
        }

        private fun getIDComponent(view: View): String {
            val idName = view.resources.getResourceName(view.id)?.split("id/".toRegex())
                ?.dropLastWhile { it.isEmpty() }
                ?.toTypedArray()
            return idName?.get(1) ?: ""
        }

        private fun getString(context: Context, idName: String): String {
            val res = context.resources.getIdentifier(idName, "string", context.packageName)
            return if (res != 0) context.getString(res) else ""
        }

    }
}