package com.noblemajesty.marvel.presenters

import com.noblemajesty.marvel.contracts.MainActivityContract
import com.noblemajesty.marvel.utils.MarvelNetworkCall
import com.noblemajesty.marvel.utils.SecretUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class MainActivityPresenter(val view: MainActivityContract.MainActivityView):
        MainActivityContract.MainActivityPresenterInterface {
    private lateinit var getAllCharactersDisposable: Disposable
    private val compositeDisposable = CompositeDisposable()

    override fun getAllMarvelCharacters() {
        getAllCharactersDisposable = MarvelNetworkCall
                .getMarvelCharacters(SecretUtils.publicKey, SecretUtils.privateKey)
                .subscribe({ it -> view.onGetAllMarvelCharacterSuccess(it) },
                        { view.onGetAllMarvelCharacterError() })
        compositeDisposable.add(getAllCharactersDisposable)
    }

    override fun getAllComics() { }

    override fun onStop() {
        compositeDisposable.clear()
    }
}