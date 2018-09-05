package com.noblemajesty.marvel.network

import okhttp3.Interceptor
import okhttp3.Response

class MarvelInterceptor (private val publicKey: String, private val privateKey: String) :
        Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url()
                .newBuilder()
                .addQueryParameter("apiKey", publicKey)
                .build()

        val newRequestBody = request.newBuilder()
                .url(url)
                .build()

        return chain.proceed(newRequestBody)
    }
}