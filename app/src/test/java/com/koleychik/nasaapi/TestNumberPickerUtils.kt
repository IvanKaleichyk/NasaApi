package com.koleychik.nasaapi

import com.koleychik.nasaapi.utils.GetDateModel
import com.koleychik.nasaapi.utils.NumberPickerUtils
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TestNumberPickerUtils {

    @Test
    fun checkDate(){
        val date2015 = GetDateModel.get2015()
        val dateNow = GetDateModel.get()
        println(date2015)
        println(dateNow)
        val arrDate = NumberPickerUtils.getList(date2015.day.toInt(), dateNow.day.toInt())
        val arr = NumberPickerUtils.getList(date2015.month.toInt(), dateNow.month.toInt())
        showItem(arrDate)
        showItem(arr)
        assertThat(arr).contains("10")
    }

    private fun showItem(arr : Array<String>){
        for (i in arr) print("i = $i")
        println()
    }

}