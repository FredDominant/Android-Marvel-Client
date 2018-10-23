package com.noblemajesty.marvel.contracts

import com.noblemajesty.marvel.models.getCharacters.MarvelCharacters

interface MainActivityContract {
    interface MainActivityView{
        fun onGetAllMarvelCharacterSuccess(marvelCharacters: MarvelCharacters?)
        fun onGetAllMarvelCharacterError()
    }

    interface MainActivityPresenterInterface {
        fun getAllComics()
        fun getAllMarvelCharacters()
        fun onStop()
    }
}