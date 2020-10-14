package com.koleychik.nasaapi.network.api

import com.koleychik.nasaapi.models.MarsModelList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MarsPhotosApi {

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getBy(
//        @Query("earth_date") earth_date : String
//        @Query("sol") sol : Int = 30,
//        @Query("earth_date") earth_date: String ,
        @QueryMap options: Map<String, String>
    ) : Response<MarsModelList>

//    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=10&api_key=hEeqLXHoCUIBgzosrOfJFb7r4kZ8vJJCuUYAVdUt")
//    suspend fun test(): Response<Any>
//
//    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=10&api_key=hEeqLXHoCUIBgzosrOfJFb7r4kZ8vJJCuUYAVdUt")
//    suspend fun testList() : Response<List<Image>>

}