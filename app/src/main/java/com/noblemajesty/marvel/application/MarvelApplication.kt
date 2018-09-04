package com.noblemajesty.marvel.application

import android.app.Application
import android.util.Log

class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.e("Marvel", "Application started!")
    }

}