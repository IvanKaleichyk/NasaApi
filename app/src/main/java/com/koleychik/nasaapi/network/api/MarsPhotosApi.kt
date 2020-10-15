package com.koleychik.nasaapi.network.api

import com.koleychik.nasaapi.models.MarsModelList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MarsPhotosApi {

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getBy(
        @QueryMap options: Map<String, String>
    ) : Response<MarsModelList>
}