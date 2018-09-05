package com.noblemajesty.marvel.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MarvelRetrofitBuilder (private val pk: String, private val prk: String) {

    val baseUrl = "https://gateway.marvel.com/"

    fun getInterceptor() : Interceptor = MarvelInterceptor(pk, prk)

    fun getClient() : OkHttpClient {
        return OkHttpClient()
                .newBuilder()
                .addInterceptor(getInterceptor())
                .build()
    }

    fun getBuilder() : Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build()!!
    }

    fun getService() : MarvelService? = getBuilder().create(MarvelService::class.java)

}