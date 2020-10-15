package com.koleychik.nasaapi.network.api

import com.koleychik.nasaapi.models.mainModels.EarthImageModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EarthImagesApi {

    @GET("EPIC/api/natural/date/{date}")
    suspend fun getEarthImagesByDate(
        @Path("date") date : String,
    ) : Response<List<EarthImageModel>>

}