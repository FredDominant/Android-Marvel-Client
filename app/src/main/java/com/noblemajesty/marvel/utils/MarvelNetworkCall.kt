package com.noblemajesty.marvel.utils

import com.noblemajesty.marvel.models.getCharacters.MarvelCharacters
import com.noblemajesty.marvel.network.MarvelRetrofitBuilder
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object MarvelNetworkCall {

    fun getMarvelCharacters (publicKey: String, privateKey: String) : Flowable<MarvelCharacters> {
        val timeStamp = System.currentTimeMillis().div(1000).toString()
        val hash = HashUtil.hashWithAlgorithm(stringToBeHashed = timeStamp + privateKey + publicKey)
        val marvelRetrofitBuilder = MarvelRetrofitBuilder(timeStamp, publicKey, hash).getService()

        return marvelRetrofitBuilder.listCharacters(HashMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

}