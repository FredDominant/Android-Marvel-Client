package com.noblemajesty.marvel.contracts

interface MainActivityContract {
    interface MainActivityView{
        fun onCreate()
    }

    interface MainActivityPresenterInterface {
        fun getAllComics()
    }
}