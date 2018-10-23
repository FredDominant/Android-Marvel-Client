package com.noblemajesty.marvel.utils

import com.noblemajesty.marvel.models.getCharacters.MarvelCharacters
import com.noblemajesty.marvel.models.getComics.MarvelComics
import com.noblemajesty.marvel.network.MarvelRetrofitBuilder
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object MarvelNetworkCall {

    fun getMarvelCharacters (publicKey: String, privateKey: String) : Flowable<MarvelCharacters> {
        val timeStamp = getTimeStamp()
        val hash = HashUtil.hashWithAlgorithm(stringToBeHashed = timeStamp + privateKey + publicKey)
        val marvelRetrofitBuilder = MarvelRetrofitBuilder(timeStamp, publicKey, hash).getService()
        return marvelRetrofitBuilder.listCharacters(HashMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

    fun getMarvelComics(publicKey: String, privateKey: String) : Flowable<MarvelComics> {
        val timeStamp = getTimeStamp()
        val hash = HashUtil.hashWithAlgorithm(stringToBeHashed = timeStamp + privateKey + publicKey)
        return MarvelRetrofitBuilder(timeStamp, publicKey, hash).getService()
                .listComics()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getTimeStamp() : String {
        return System.currentTimeMillis().div(1000).toString()
    }

}