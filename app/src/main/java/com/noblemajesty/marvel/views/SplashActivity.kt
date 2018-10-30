package com.noblemajesty.marvel.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.noblemajesty.marvel.contracts.MainActivityContract
import com.noblemajesty.marvel.models.getCharacters.MarvelCharacters
import com.noblemajesty.marvel.models.getComics.MarvelComics
import com.noblemajesty.marvel.presenters.MainActivityPresenter
import com.noblemajesty.marvel.utils.NetworkConnectivity

class SplashActivity : AppCompatActivity(), MainActivityContract.MainActivityView {


    private lateinit var mainActivityPresenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityPresenter = MainActivityPresenter(this, this)

        val intentResult = intent.getBooleanExtra("Exit", false)

        if (intentResult) return finish()

        val connectivityChecker = NetworkConnectivity(this@SplashActivity).isConnected()

        if (connectivityChecker) {
            mainActivityPresenter.getAllMarvelCharacters()
        } else { sendErrorIntent() }
    }

    private fun sendErrorIntent() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        intent.putExtra("Connectivity", false)
        startActivity(intent)
    }
    override fun onGetAllMarvelCharacterError() = sendErrorIntent()

    override fun onGetAllMarvelCharacterSuccess(marvelCharacters: MarvelCharacters?) {
        marvelCharacters?.let { it ->
            val mainActivityIntent = Intent(this@SplashActivity, MainActivity::class.java)
            intent.putExtra("Characters", it.toJson().toString())
            startActivity(mainActivityIntent)
        }
    }
    override fun onGetMarvelComicsSuccess(marvelComics: MarvelComics?) { }

    override fun onGetMarvelComicsError(it: Throwable) { }
}

