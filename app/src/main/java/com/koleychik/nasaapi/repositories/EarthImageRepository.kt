package com.koleychik.nasaapi.repositories

import com.koleychik.nasaapi.network.RetrofitInstance

class EarthImageRepository {
    suspend fun getEarthImagesByDate(date: String) =
        RetrofitInstance().apiEarthImages.getEarthImagesByDate(date)
}