package com.koleychik.nasaapi.utils

import com.koleychik.nasaapi.models.DateModel
import java.lang.StringBuilder

class StringUtils {

    companion object {
        fun maxLength(text: String, maxLength: Int): String {
            if (text.length <= maxLength) return text

            val arr = text.toCharArray()
            val stringBuilder = StringBuilder()
            stringBuilder.append(arr)
            stringBuilder.append('.')
            stringBuilder.append('.')
            stringBuilder.append('.')

            return stringBuilder.toString()
        }

        fun getDateString(dateModel: DateModel, divide: String) : String{
            val sb = StringBuilder()
            sb.append(dateModel.year).append(divide)
            sb.append(dateModel.month).append(divide)
            sb.append(dateModel.day)
            return sb.toString()
        }

        fun getDateString(time: Long?, divide: String) : String{
            val dateModel = GetDateModel.get(time)
            val sb = StringBuilder()
            sb.append(dateModel.year).append(divide)
            sb.append(dateModel.month).append(divide)
            sb.append(dateModel.day)
            return sb.toString()
        }

        fun getDateString(year: String, month : String, day : String, divide: String) : String{
            val sb = StringBuilder()
            sb.append(year).append(divide)
            sb.append(month).append(divide)
            sb.append(day)
            return sb.toString()
        }

//        fun getDateStringUrl(dateModel: DateModel) : String{
//            val sb = StringBuilder()
//            sb.append(dateModel.year).append("/")
//            sb.append(dateModel.month).append("/")
//            sb.append(dateModel.day)
//            return sb.toString()
//        }

//        fun getDateString(year: Any, month : Any, day : Any) : String{
//            val sb = StringBuilder()
//            sb.append(year as String).append("-")
//            sb.append(month as String).append("-")
//            sb.append(day as String)
//            return sb.toString()
//        }

    }

}