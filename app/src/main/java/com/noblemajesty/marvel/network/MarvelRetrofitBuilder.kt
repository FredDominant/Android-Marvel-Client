package com.noblemajesty.marvel.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MarvelRetrofitBuilder (private val timeStamp: String, private val pk: String, private val hash: String) {

    companion object {
        const val baseUrl = "https://gateway.marvel.com/"
    }

    private fun getInterceptor() : Interceptor = MarvelInterceptor(timeStamp, pk, hash)

    private fun getHttpLoggingInterceptor() : HttpLoggingInterceptor{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    private fun getClient() : OkHttpClient {
        return OkHttpClient()
                .newBuilder()
                .addInterceptor(getInterceptor())
                .addInterceptor(getHttpLoggingInterceptor())
                .build()
    }

    private fun getBuilder() : Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getClient())
                .build()
    }

    fun getService() : MarvelService {
        return getBuilder().create(MarvelService::class.java)
    }

}