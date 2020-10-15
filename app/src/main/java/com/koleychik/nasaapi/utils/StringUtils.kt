package com.koleychik.nasaapi.utils

import com.koleychik.nasaapi.models.DateModel
import java.lang.StringBuilder

class StringUtils {

    companion object {
        fun getDateString(dateModel: DateModel, divide: String): String {

            var month = dateModel.month
            if (month.length == 1) month = "0$month"

            val sb = StringBuilder()
            sb.append(dateModel.year).append(divide)
            sb.append(month).append(divide)
            sb.append(dateModel.day)
            return sb.toString()
        }

        fun saveSameSymbols(string: String, value: Int): String {
            val sb = StringBuilder(string)

            if (sb.length - value < 0) return ""

            sb.deleteRange(value, sb.length)
            return sb.toString()
        }

        fun setHttpToHttps(path: String): String {
            val arrPath = path.split(":")
            if (arrPath[0].length > 4) return path

            val sb = StringBuilder(path)

            val res = StringBuilder(arrPath[0] + "s")
            val start = getFirst(path, ':')


            for (i in (start until sb.length)) {
                res.append(sb[i])
            }
            return res.toString()
        }

        private fun getFirst(string: String, value: Char): Int {
            val sb = StringBuilder(string)
            for (i in (sb.indices)) {
                if (sb[i] == value) return i
            }
            return 0
        }
    }
}