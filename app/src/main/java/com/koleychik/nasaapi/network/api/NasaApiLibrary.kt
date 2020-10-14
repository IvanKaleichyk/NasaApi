package com.koleychik.nasaapi.network.api

import com.koleychik.nasaapi.models.mainModels.NasaLibraryModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NasaApiLibrary {

    @GET("search")
    suspend fun getByKeyWords(
        @QueryMap options: Map<String, String>
    ): Response<List<NasaLibraryModel>>

}