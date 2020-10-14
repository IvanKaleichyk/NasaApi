package com.koleychik.nasaapi.models

data class NumberPickersData(
    val minValueDates: Int, val maxValueDates: Int,
    val minValueMonths: Int, val maxValueMonths: Int,
    val minValueYears: Int, val maxValueYears: Int
)