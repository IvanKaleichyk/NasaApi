package com.koleychik.nasaapi.utils

import com.koleychik.nasaapi.models.DateModel

fun getImageUri(date: String, url: String): String {
    //https://api.nasa.gov/EPIC/archive/enhanced/2016/12/04/png/epic_RBG_20161204003633.png?api_key=DEMO_KEY
    val sb = StringBuilder()
    sb.append("https://api.nasa.gov/EPIC/archive/natural/")

    val dateModel = splitStringDate(date)

    sb.append(dateModel.year).append('/')
        .append(dateModel.month).append('/')
        .append(dateModel.day).append("/png/")
        .append(url).append(".png?api_key=").append(Constants.KEY)
    return sb.toString()
}

private fun splitStringDate(date: String): DateModel {
    val dateComponents = date.split("-").toMutableList()

    dateComponents[2] = StringUtils.saveSameSymbols(dateComponents[2], 2)
    if (dateComponents[1].length == 1) dateComponents[1] = "0" + dateComponents[1]
    return DateModel(
        dateComponents[2],
        dateComponents[1],
        dateComponents[0]
    )
}