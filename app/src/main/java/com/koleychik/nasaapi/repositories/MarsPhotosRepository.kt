package com.koleychik.nasaapi.repositories

import com.koleychik.nasaapi.network.RetrofitInstance

class MarsPhotosRepository {

    suspend fun getBy(options: Map<String, String>) =
        RetrofitInstance().apiMarsPhotos.getBy(options)
}