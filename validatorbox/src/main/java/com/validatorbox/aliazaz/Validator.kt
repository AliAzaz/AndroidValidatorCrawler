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
        fun emptyTextBox(context: Context, txt: EditText, msg: String): Boolean {
            return if (TextUtils.isEmpty(txt.text.toString())) {

                ValidatorError.putError(context, txt)


                Toast.makeText(context, "ERROR(Empty): $msg", Toast.LENGTH_SHORT).show()
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
        fun emptyTextView(context: Context, txt: TextView, msg: String): Boolean {
            return if (TextUtils.isEmpty(txt.text.toString())) {
                Toast.makeText(context, "ERROR(Empty): $msg", Toast.LENGTH_SHORT).show()
                txt.error = "Required"    // Set Error on last radio button
                txt.requestFocus()
                Log.i(
                    context.javaClass.name,
                    "${context.resources.getResourceEntryName(txt.id)} : Required"
                )
                false
            } else {
                txt.error = null
                txt.clearFocus()
                true
            }

        }

        @JvmStatic
        fun emptyCustomTextBox(context: Context, txt: EditText, msg: String, flag: Boolean) {
            if (flag) {
                Toast.makeText(context, "ERROR: $msg", Toast.LENGTH_SHORT).show()
                txt.error = msg
                txt.requestFocus()
                Log.i(
                    context.javaClass.name,
                    "${context.resources.getResourceEntryName(txt.id)} : $msg"
                )
            } else {
                txt.error = null
                txt.clearFocus()
            }
        }

        @JvmStatic
        fun emptyEditTextPicker(context: Context, txt: EditText, msg: String): Boolean {
            var messageConv = ""
            var flag = true
            if (!(txt as EditTextPicker).isEmptyTextBox) {
                flag = false
                messageConv = "ERROR(Empty)"
            } else if (!txt.isRangeTextValidate) {
                flag = false
                messageConv = "ERROR(range)"
            } else if (!txt.isTextEqualToPattern) {
                flag = false
                messageConv = "ERROR(pattern)"
            }

            return if (!flag) {

                ValidatorError.putError(context, txt)

                Toast.makeText(context, "$messageConv: $msg", Toast.LENGTH_SHORT).show()
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
            msg: String,
            type: String
        ): Boolean {

            return if (Integer.valueOf(txt.text.toString()) < min || Integer.valueOf(txt.text.toString()) > max) {
                Toast.makeText(context, "ERROR(Invalid): $msg", Toast.LENGTH_SHORT).show()
                txt.error = "Range is $min to $max ($type)"    // Set Error on last radio button
                txt.requestFocus()
                Log.i(
                    context.javaClass.name,
                    "${context.resources.getResourceEntryName(txt.id)} : Range is $min to $max times"
                )
                false
            } else {
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
            msg: String,
            type: String
        ): Boolean {

            return if (java.lang.Double.valueOf(txt.text.toString()) < min || java.lang.Double.valueOf(
                    txt.text.toString()
                ) > max
            ) {
                Toast.makeText(context, "ERROR(Invalid): $msg", Toast.LENGTH_SHORT).show()
                txt.error = "Range is $min to $max ($type)"    // Set Error on last radio button
                txt.requestFocus()
                Log.i(
                    context.javaClass.name,
                    "${context.resources.getResourceEntryName(txt.id)} : Range is $min to $max times"
                )
                false
            } else {
                txt.error = null
                txt.clearFocus()
                true
            }
        }

        @JvmStatic
        fun emptySpinner(context: Context, spin: Spinner, msg: String): Boolean {
            return if (spin.selectedItemPosition == 0) {
                Toast.makeText(context, "ERROR(Empty): $msg", Toast.LENGTH_SHORT).show()
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
                (spin.selectedView as TextView).error = null
                true
            }
        }

        @JvmStatic
        fun emptyRadioButton(
            context: Context,
            rdGrp: RadioGroup,
            rdBtn: RadioButton,
            msg: String
        ): Boolean {
            if (rdGrp.checkedRadioButtonId == -1) {
                Toast.makeText(context, "ERROR(Empty): $msg", Toast.LENGTH_SHORT).show()
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
                            if (innerV is EditTextPicker)
                                rdbFlag = emptyEditTextPicker(
                                    context,
                                    innerV as EditText,
                                    getString(context, getIDComponent(innerV))
                                )
                            else
                                rdbFlag = emptyTextBox(
                                    context,
                                    innerV,
                                    getString(context, getIDComponent(innerV))
                                )
                    }
                    if (!rdbFlag) break
                }

                return if (rdbFlag) {
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
            container: LinearLayout,
            cbx: CheckBox,
            msg: String
        ): Boolean {

            var flag = false
            for (i in 0 until container.childCount) {
                val v = container.getChildAt(i)
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

                        for (j in 1 until container.childCount) {
                            val innerV = container.getChildAt(j)
                            if (innerV is EditText) {
                                if (getIDComponent(v) == innerV.getTag()) {
                                    if (innerV is EditTextPicker)
                                        flag = emptyEditTextPicker(
                                            context,
                                            innerV as EditText,
                                            getString(context, getIDComponent(innerV))
                                        )
                                    else
                                        flag = emptyTextBox(
                                            context,
                                            innerV,
                                            getString(context, getIDComponent(innerV))
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
                Toast.makeText(context, "ERROR(Empty): $msg", Toast.LENGTH_SHORT).show()
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
        fun emptyCheckingContainer(context: Context, lv: ViewGroup): Boolean {

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

                        val asNamed = getString(context, getIDComponent(view))

                        if (!emptyRadioButton(context, view, v as RadioButton, asNamed)) {
                            return false
                        }
                    }

                } else if (view is Spinner) {
                    if (!emptySpinner(context, view, getString(context, getIDComponent(view)))) {
                        return false
                    }
                } else if (view is EditText) {
                    if (view is EditTextPicker) {
                        if (!emptyEditTextPicker(
                                context,
                                view as EditText,
                                getString(context, getIDComponent(view))
                            )
                        )
                            return false
                    } else {
                        if (!emptyTextBox(
                                context,
                                view,
                                getString(context, getIDComponent(view))
                            )
                        ) {
                            return false
                        }
                    }
                } else if (view is CheckBox) {
                    if (!view.isChecked) {
                        view.error = getString(context, getIDComponent(view))
                        Toast.makeText(
                            context,
                            "ERROR(Empty): " + getString(context, getIDComponent(view)),
                            Toast.LENGTH_SHORT
                        ).show()
                        return false
                    }
                } else if (view is LinearLayout) {
                    if (view.getTag() != null && view.getTag() == "0") {
                        if (!emptyCheckBox(
                                context, view,
                                view.getChildAt(0) as CheckBox,
                                getString(context, getIDComponent(view.getChildAt(0)))
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