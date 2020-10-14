package com.koleychik.nasaapi.utils

fun getImageUri(date: String, url : String): String {
    //https://api.nasa.gov/EPIC/archive/enhanced/2016/12/04/png/epic_RBG_20161204003633.png?api_key=DEMO_KEY
    val sb = StringBuilder()
    sb.append("https://api.nasa.gov/EPIC/archive/natural/")
    val dateComponents = date.split("-")
    sb.append(dateComponents[0]).append('/')
        .append(dateComponents[1]).append('/')
        .append(dateComponents[2]).append("/png/")
        .append(url).append(".png?api_key=").append(Constants.KEY)
    return sb.toString()

}