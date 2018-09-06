package com.noblemajesty.marvel.network

import com.noblemajesty.marvel.models.getCharacters.MarvelCharacters
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MarvelService {
    @GET("/v1/public/characters")
    fun listCharacters(@QueryMap options: Map<String, String>): Call<MarvelCharacters>
}