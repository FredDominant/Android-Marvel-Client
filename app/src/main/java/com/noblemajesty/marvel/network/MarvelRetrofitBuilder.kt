package com.noblemajesty.marvel.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MarvelRetrofitBuilder (private val timeStamp: String, private val pk: String, private val hash: String) {

    val baseUrl = "https://gateway.marvel.com/"

    fun getInterceptor() : Interceptor = MarvelInterceptor(timeStamp, pk, hash)

    fun getHttpLoggingInterceptor() : HttpLoggingInterceptor{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    fun getClient() : OkHttpClient {
        return OkHttpClient()
                .newBuilder()
                .addInterceptor(getInterceptor())
                .addInterceptor(getHttpLoggingInterceptor())
                .build()
    }

    fun getBuilder() : Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build()!!
    }

    fun getService() : MarvelService {
        return getBuilder().create(MarvelService::class.java)
    }

}