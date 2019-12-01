package com.validatorbox.aliazaz

import android.view.View
import android.view.ViewGroup
import android.widget.*


class Clear {
    companion object {

        @JvmStatic
        fun clearRadioButton(container: LinearLayout, rdGrp: RadioGroup) {
            if (rdGrp.checkedRadioButtonId == -1) {
                rdGrp.clearCheck()
                for (i in 0 until container.childCount) {
                    val v: View = container.getChildAt(i)
                    if (v is RadioButton) {
                        v.setEnabled(false)
                    }
                }
            }
        }

        @JvmStatic
        fun clearCheckBoxes(container: ViewGroup) {
            for (i in 0 until container.childCount) {
                val v: View = container.getChildAt(i)
                if (v is CheckBox) {
                    v.isChecked = false
                    v.setEnabled(false)
                }
            }
        }

        @JvmStatic
        fun clearAllFields(container: View, flag: Boolean? = null) {
            for (i in 0 until (container as ViewGroup).childCount) {
                val v: View = container.getChildAt(i)
                if (v is CheckBox) {
                    v.isChecked = false
                    v.error = null
                    if (flag != null) v.setEnabled(flag)
                } else if (v is RadioGroup) {
                    v.clearCheck()
                    if (flag != null) {
                        for (j in 0 until v.childCount) {
                            v.getChildAt(j).isEnabled = flag
                        }
                    }
                } else if (v is EditText) {
                    v.text = null
                    v.clearFocus()
                    if (flag != null) v.setEnabled(flag)
                } else if (v is RadioButton) {
                    if (flag != null) v.setEnabled(flag)
                } else if (v is ViewGroup) {
                    clearAllFields(v, flag)
                }
            }
        }
    }
}