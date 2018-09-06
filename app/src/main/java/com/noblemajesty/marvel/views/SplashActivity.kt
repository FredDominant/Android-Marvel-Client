package com.noblemajesty.marvel.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.noblemajesty.marvel.models.getCharacters.MarvelCharacters
import com.noblemajesty.marvel.network.MarvelRetrofitBuilder
import com.noblemajesty.marvel.utils.HashUtil
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

        val dataFromServer = async(CommonPool) {
            try {
                getMarvelCharacters("68fc19df163796f9a709aea430733b2c", "32b4fd917550adcbb2b5e9e78dd6e6ed4e7c41e5")

            } catch (error: Exception) {
                error.printStackTrace()
            }
        }

        launch(CommonPool) {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            val data = dataFromServer.await() as MarvelCharacters

            intent.putExtra("Characters", data.toJson().toString())
            startActivity(intent)
        }

    }

    suspend fun getMarvelCharacters (publicKey: String, privateKey: String) : MarvelCharacters? {

        val timeStamp = System.currentTimeMillis().div(1000).toString()

        val hash = HashUtil.hashWithAlgorithm(stringToBeHashed = timeStamp + privateKey + publicKey)

        val marvelRetrofitBuilder = MarvelRetrofitBuilder(timeStamp, publicKey, hash).getService()

        return marvelRetrofitBuilder.listCharacters(HashMap()).execute().body()

    }
}

