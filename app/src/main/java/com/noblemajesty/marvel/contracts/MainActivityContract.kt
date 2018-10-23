package com.noblemajesty.marvel.contracts

import com.noblemajesty.marvel.models.getCharacters.MarvelCharacters
import com.noblemajesty.marvel.models.getComics.MarvelComics

interface MainActivityContract {

    interface MainActivityView{
        fun onGetAllMarvelCharacterSuccess(marvelCharacters: MarvelCharacters?)
        fun onGetAllMarvelCharacterError()
        fun onGetMarvelComicsSuccess(marvelComics: MarvelComics?)
        fun onGetMarvelComicsError()
    }

    interface MainActivityPresenterInterface {
        fun getAllComics()
        fun getAllMarvelCharacters()
        fun getMarvelStories()
        fun onStop()
    }
}