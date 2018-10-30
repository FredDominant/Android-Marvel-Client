package com.noblemajesty.marvel.presenters

import android.content.Context
import android.util.Log
import com.noblemajesty.marvel.contracts.MainActivityContract
import com.noblemajesty.marvel.utils.MarvelNetworkCall
import com.noblemajesty.marvel.utils.SecretUtils
import io.reactivex.disposables.CompositeDisposable

class MainActivityPresenter(private val context: Context, val view: MainActivityContract.MainActivityView):
        MainActivityContract.MainActivityPresenterInterface {

    override fun getMarvelStories() {

    }

    private val compositeDisposable = CompositeDisposable()

    override fun getAllMarvelCharacters() {
        val getAllCharactersDisposable = MarvelNetworkCall
                .getMarvelCharacters(context, SecretUtils.publicKey, SecretUtils.privateKey)
                .subscribe({ it -> view.onGetAllMarvelCharacterSuccess(it) },
                        { view.onGetAllMarvelCharacterError() })
        compositeDisposable.add(getAllCharactersDisposable)
    }

    override fun getAllComics() {
        Log.e("Method", "callllllllllllllllllllleeeeeeedddddddd")
        val getAllComics = MarvelNetworkCall
                .getMarvelComics(context, SecretUtils.publicKey, SecretUtils.privateKey)
                .subscribe({ it ->
                    Log.e("Api", "calllllllllllllllllllll")
                    view.onGetMarvelComicsSuccess(it) },
                        { it -> view.onGetMarvelComicsError(it) })
        compositeDisposable.addAll(getAllComics)
    }

    override fun onStop() {
        compositeDisposable.dispose()
    }
}