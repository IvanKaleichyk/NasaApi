package com.koleychik.nasaapi.utils

import com.koleychik.nasaapi.models.NumberPickersData

class NumberPickerUtils {

    companion object {
        fun createNumberPickerData(): NumberPickersData {
            val dateNow = GetDateModel.getMaxForSpinner()
            val date2015 = GetDateModel.get2015()
            return NumberPickersData(
                date2015.day.toInt(), dateNow.day.toInt(),
                date2015.month.toInt(), dateNow.month.toInt(),
                date2015.year.toInt(), dateNow.year.toInt()
            )
        }

        fun getList(minValue : Int, maxValue : Int): Array<String>{
            val list = mutableListOf<String>()
            for ( i in (minValue..maxValue)){
                list.add(i.toString())
            }
            return list.toTypedArray()
        }

    }

}