package com.noblemajesty.marvel.network

import okhttp3.Interceptor
import okhttp3.Response

class MarvelInterceptor (private val timeStamp: String, private val publicKey: String, private val hash: String) :
        Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url()
                .newBuilder()
                .addQueryParameter("apikey", publicKey)
                .addQueryParameter("ts", timeStamp)
                .addQueryParameter("hash", hash)
                .build()

        val newRequestBody = request.newBuilder()
                .url(url)
                .build()

        return chain.proceed(newRequestBody)
    }
}