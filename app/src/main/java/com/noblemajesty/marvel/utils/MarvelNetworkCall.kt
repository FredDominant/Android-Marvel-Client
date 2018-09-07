package com.noblemajesty.marvel.utils

import com.noblemajesty.marvel.models.getCharacters.MarvelCharacters
import com.noblemajesty.marvel.network.MarvelRetrofitBuilder

object MarvelNetworkCall {

    suspend fun getMarvelCharacters (publicKey: String, privateKey: String) : MarvelCharacters? {
        val timeStamp = System.currentTimeMillis().div(1000).toString()
        val hash = HashUtil.hashWithAlgorithm(stringToBeHashed = timeStamp + privateKey + publicKey)
        val marvelRetrofitBuilder = MarvelRetrofitBuilder(timeStamp, publicKey, hash).getService()
        return marvelRetrofitBuilder.listCharacters(HashMap()).execute().body()
    }

}