package com.koleychik.nasaapi.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.widget.NumberPicker
import com.koleychik.nasaapi.R
import com.koleychik.nasaapi.callbacks.ChooseDateCallback
import com.koleychik.nasaapi.utils.NumberPickerUtils
import kotlinx.android.synthetic.main.dialog_choose_date.*

class ChooseDateDialog(context: Context, callback: ChooseDateCallback) {

    private val dialog = Dialog(context)

    init {
        dialog.setContentView(R.layout.dialog_choose_date)

        workWithNumberPicker()

        dialog.btnSearch.setOnClickListener {
            callback.start(
                dialog.day.value.toString(),
                dialog.month.value.toString(),
                dialog.year.value.toString()
            )
            dialog.dismiss()
        }
    }

    fun show(day : Int, month : Int, year : Int) {
        dialog.day.value = day
        dialog.month.value = month
        dialog.year.value = year
        dialog.show()
    }

    private fun workWithNumberPicker() {
        val date = NumberPickerUtils.createNumberPickerData()

        createNumberPickers(dialog.day, date.minValueDates, date.maxValueDates)
        createNumberPickers(dialog.month, date.minValueMonths, date.maxValueMonths)
        createNumberPickers(dialog.year, date.minValueYears, date.maxValueYears)
    }


    private fun createNumberPickers(
        numberPicker: NumberPicker,
        minValue: Int,
        maxValue: Int,
        wrapSelectorWheel: Boolean = true
    ) {
        numberPicker.minValue = minValue
        numberPicker.maxValue = maxValue
        numberPicker.wrapSelectorWheel = wrapSelectorWheel
        numberPicker.displayedValues = NumberPickerUtils.getList(minValue, maxValue)
    }
}