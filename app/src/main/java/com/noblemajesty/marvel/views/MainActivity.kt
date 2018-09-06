package com.noblemajesty.marvel.views

import android.app.SearchManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.noblemajesty.marvel.R
import com.noblemajesty.marvel.contracts.MainActivityContract
import com.noblemajesty.marvel.models.getCharacters.MarvelCharacters
import com.noblemajesty.marvel.network.MarvelRetrofitBuilder
import com.noblemajesty.marvel.presenters.MainActivityPresenter
import com.noblemajesty.marvel.utils.HashUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), MainActivityContract.MainActivityView {
    val mainPresenter = MainActivityPresenter(this)
    override fun onCreate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentResult = intent.getBooleanExtra("Exit", false)

        val allMarvelCharacters = async(CommonPool){
            try {
                getMarvelCharacters("68fc19df163796f9a709aea430733b2c", "32b4fd917550adcbb2b5e9e78dd6e6ed4e7c41e5")
            } catch (error: Exception) {
                Log.e("Network Error:", error.localizedMessage)
            }
        }

        launch(CommonPool) {
            val apiResponse = allMarvelCharacters.await()
            Log.e("Api response", "$apiResponse")
        }


        if (intentResult) {
            return finish()
        }

        if (intent.action == Intent.ACTION_SEARCH) {
            val query = intent.getStringExtra(SearchManager.QUERY) as String
        }

        val bottomNavigation = bottom_navigation_main_activity
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.marvel_characters -> {
                    toast("Character selected").show()
                    true }
                R.id.marvel_creators -> {
                    toast("Creators").show()
                    true }
                R.id.marvel_stories -> {

                    toast("Stories selected").show()
                    true }
                R.id.marvel_events -> {
                    toast("Events selected").show()
                    true }

                else -> true
            }
        }
    }

    fun getMarvelCharacters (publicKey: String, privateKey: String) : MarvelCharacters? {

        val timeStamp = System.currentTimeMillis().div(1000).toString()

        val hash = HashUtil.hashWithAlgorithm(stringToBeHashed = timeStamp + privateKey + publicKey)

        val marvelRetrofitBuilder = MarvelRetrofitBuilder(timeStamp, publicKey, hash).getService()

        return marvelRetrofitBuilder.listCharacters(HashMap()).execute().body()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.action_search_marvel -> {
                onSearchRequested()
                true
            }
            else -> true
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this@MainActivity, SplashActivity::class.java)
        intent.putExtra("Exit", true)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        return super.onBackPressed()
    }

}
