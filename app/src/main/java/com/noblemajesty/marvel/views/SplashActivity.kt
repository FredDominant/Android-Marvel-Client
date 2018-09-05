package com.noblemajesty.marvel.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intentResult = intent.getBooleanExtra("Exit", false)

        if (intentResult) {
            return finish()
        }

        val dataFromServer = async(CommonPool) {
            getDataFromServer()
        }

        launch(CommonPool) {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            intent.putExtra("Server", dataFromServer.await())
            startActivity(intent)
        }

    }

    suspend fun getDataFromServer() : String {
        //TODO make API call here
        delay(4000)
        return "Some returned value from the API call"
    }
}
