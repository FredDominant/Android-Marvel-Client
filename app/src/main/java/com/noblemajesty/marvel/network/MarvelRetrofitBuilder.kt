package com.noblemajesty.marvel.network

import android.content.Context
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.lang.Exception

class MarvelRetrofitBuilder (private val context: Context, private val timeStamp: String, private val pk: String, private val hash: String) {

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
        val client = OkHttpClient.Builder().apply {
            cache(getCache())
            addInterceptor(getInterceptor())
            addInterceptor(getHttpLoggingInterceptor())
            addInterceptor { it ->
                    try {
                    it.proceed(it.request())
                    } catch (exception: Exception) {
                        val offlineRequest = it.request().newBuilder()
                                .header("Cache-Control", "public, only-if-cached," +
                                        "max-stale=" + 60 * 60 * 24)
                                .build()
                            it.proceed(offlineRequest)

                    }
                }
            }
        return client.build()
    }

    private fun getBuilder() : Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getClient())
                .build()
    }

    private fun getCache() : Cache {
        val httpCacheDirectory = File(context.cacheDir, "httpCache")
        return Cache(httpCacheDirectory, 10 * 1024 * 1024)
    }

    fun getService() : MarvelService {
        return getBuilder().create(MarvelService::class.java)
    }

}