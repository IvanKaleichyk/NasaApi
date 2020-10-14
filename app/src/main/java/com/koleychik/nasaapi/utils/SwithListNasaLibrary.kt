package com.koleychik.nasaapi.utils

import com.koleychik.nasaapi.models.mainModels.NasaLibraryModel

fun switchList(list: List<NasaLibraryModel>, switchNumber: Int): List<NasaLibraryModel> {
    var type = ""
    val returnList = mutableListOf<NasaLibraryModel>()
    when (switchNumber) {
        0 -> return list
        1 -> type = "image"
        2 -> type = "video"
    }
    for (i in list) {
        if (i.data.media_type == type) returnList.add(i)
    }
    return returnList
}