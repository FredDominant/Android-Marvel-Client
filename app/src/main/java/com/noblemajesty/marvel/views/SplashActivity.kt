package com.noblemajesty.marvel.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.noblemajesty.marvel.models.getCharacters.MarvelCharacters
import com.noblemajesty.marvel.utils.MarvelNetworkCall.getMarvelCharacters
import com.noblemajesty.marvel.utils.NetworkConnectivity
import com.noblemajesty.marvel.utils.SecretUtils
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intentResult = intent.getBooleanExtra("Exit", false)

        if (intentResult) {
            return finish()
        }

        //TODO check connectivity
        val connectivityChecker = NetworkConnectivity(this@SplashActivity).isConnected()

        if (connectivityChecker) {
            val dataFromServer = async(CommonPool) {
                getMarvelCharacters(SecretUtils.publicKey, SecretUtils.privateKey)
            }

            launch(CommonPool) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                try {
                    val data = dataFromServer.await() as MarvelCharacters

                    intent.putExtra("Characters", data.toJson().toString())
                    startActivity(intent)
                } catch (error: Exception) {
                    Log.e("Network Error", error.message)
                    sendErrorIntent()
                }
            }

        } else {
            sendErrorIntent()
        }
    }

    fun sendErrorIntent() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        intent.putExtra("Connectivity", false)
        startActivity(intent)
    }

}

