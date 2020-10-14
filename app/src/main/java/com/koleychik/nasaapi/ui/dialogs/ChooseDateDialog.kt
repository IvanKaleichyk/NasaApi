package com.koleychik.nasaapi.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.NumberPicker
import com.koleychik.nasaapi.R
import com.koleychik.nasaapi.callbacks.CheckCallback
import com.koleychik.nasaapi.callbacks.ChooseDateCallback
import com.koleychik.nasaapi.utils.Constants
import com.koleychik.nasaapi.utils.GetDateModel
import com.koleychik.nasaapi.utils.NumberPickerUtils
import kotlinx.android.synthetic.main.dialog_choose_date.*
import kotlinx.android.synthetic.main.dialog_choose_date.view.*

class ChooseDateDialog(private val context: Context, callback: ChooseDateCallback) {

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

//        if (viewModel.dateModel.value == null) {
//            val model = GetDateModel.get()
//            viewModel.dateModel.value = model
//            requireView.day.value = model.day.toInt()
//            requireView.month.value = model.month.toInt()
//            requireView.year.value = model.year.toInt()
//        }

        createNumberPickers(dialog.day, date.minValueDates, date.maxValueDates)
        createNumberPickers(dialog.month, date.minValueMonths, date.maxValueMonths)
        createNumberPickers(dialog.year, date.minValueYears, date.maxValueYears)

//        val onValueChangedListener = NumberPicker.OnValueChangeListener { picker, _, newValue ->
//            Log.d(Constants.TAG, "spinner change")
//            when (picker.id) {
//                R.id.day -> viewModel.dateModel.value!!.day = newValue.toString()
//                R.id.month -> viewModel.dateModel.value!!.month = newValue.toString()
//                R.id.year -> viewModel.dateModel.value!!.year = newValue.toString()
//            }
//        }
//        requireView.day.setOnValueChangedListener { _, _, newValue ->
//            viewModel.dateModel.value!!.day = newValue.toString()
//        }
//        requireView.month.setOnValueChangedListener(onValueChangedListener)
//        requireView.year.setOnValueChangedListener(onValueChangedListener)
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