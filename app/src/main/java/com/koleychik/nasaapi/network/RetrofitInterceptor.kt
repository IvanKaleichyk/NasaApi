package com.koleychik.nasaapi.network

import com.koleychik.nasaapi.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class RetrofitInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()
        val url = originalHttpUrl.newBuilder().addQueryParameter("api_key", Constants.KEY)
            .build()
        val request = original.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}