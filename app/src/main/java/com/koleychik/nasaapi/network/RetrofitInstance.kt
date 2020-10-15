package com.koleychik.nasaapi.network

import com.koleychik.nasaapi.network.api.EarthImagesApi
import com.koleychik.nasaapi.network.api.MarsPhotosApi
import com.koleychik.nasaapi.utils.Constants
import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(RetrofitInterceptor())
    }.build()

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiMarsPhotos: MarsPhotosApi by lazy {
        retrofit.create(MarsPhotosApi::class.java)
    }

    val apiEarthImages: EarthImagesApi by lazy {
        retrofit.create(EarthImagesApi::class.java)
    }

}