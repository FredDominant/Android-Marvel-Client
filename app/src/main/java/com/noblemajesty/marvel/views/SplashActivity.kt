package com.noblemajesty.marvel.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.noblemajesty.marvel.contracts.MainActivityContract
import com.noblemajesty.marvel.models.getCharacters.MarvelCharacters
import com.noblemajesty.marvel.presenters.MainActivityPresenter
import com.noblemajesty.marvel.utils.NetworkConnectivity

class SplashActivity : AppCompatActivity(), MainActivityContract.MainActivityView {
    private lateinit var mainActivityPresenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityPresenter = MainActivityPresenter(this)

        val intentResult = intent.getBooleanExtra("Exit", false)

        if (intentResult) return finish()

        val connectivityChecker = NetworkConnectivity(this@SplashActivity).isConnected()

        if (connectivityChecker) {
            mainActivityPresenter.getAllMarvelCharacters()
//            MarvelNetworkCall.getMarvelCharacters(SecretUtils.publicKey, SecretUtils.privateKey)
//                    .subscribe(
//                            { it -> Log.e("Characters --->", "${it.data?.total}")},
//                            {})
//            val dataFromServer = async(CommonPool) {
//                getMarvelCharacters(SecretUtils.publicKey, SecretUtils.privateKey)
//            }
//
//            launch(CommonPool) {
//                val intent = Intent(this@SplashActivity, MainActivity::class.java)
//                try {
//                    val data = dataFromServer.await() as MarvelCharacters
//
//                    intent.putExtra("Characters", data.toJson().toString())
//                    startActivity(intent)
//                } catch (error: Exception) {
//                    Log.e("Network Error", error.message)
//                    sendErrorIntent()
//                }
//            }
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
}

