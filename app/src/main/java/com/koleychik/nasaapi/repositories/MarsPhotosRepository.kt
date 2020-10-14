package com.koleychik.nasaapi.repositories

import com.koleychik.nasaapi.network.RetrofitInstance
import com.koleychik.nasaapi.utils.GetDateModel
import com.koleychik.nasaapi.utils.StringUtils

class MarsPhotosRepository {

    suspend fun getBy(options: Map<String, String>) =
        RetrofitInstance().apiMarsPhotos.getBy(
//            sol,
//            StringUtils.getDateString("2020", "10", "12", "-"),
//            mapOf("earth_date" to StringUtils.getDateString("2020", "10", "12", "-"), "camera" to "FHAZ")
        options
        )
}