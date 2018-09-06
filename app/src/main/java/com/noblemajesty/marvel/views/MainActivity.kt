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
            getMarvelCharacters("abcd", "abcd")
        }

        launch(CommonPool) {
            val apiResponse = allMarvelCharacters.await()
            Log.e("Api response", apiResponse.toString())
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
                    Log.e("Hash", HashUtil.hashWithAlgorithm(stringToBeHashed = "132b4fd917550adcbb2b5e9e78dd6e6ed4e7c41e568fc19df163796f9a709aea430733b2c"))
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

    suspend fun getMarvelCharacters (publicKey: String, privateKey: String) : MarvelCharacters? {
        val marvelRetrofitBuilder = MarvelRetrofitBuilder(publicKey, privateKey).getService()
        val hashMap = HashMap<String, String>()

        hashMap["hash"] = "abcdef"
        hashMap["ts"] = System.currentTimeMillis().div(1000).toString()
        return marvelRetrofitBuilder.listCharacters(hashMap).execute().body()

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
