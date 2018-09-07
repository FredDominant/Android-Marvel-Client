package com.noblemajesty.marvel.views

import android.app.SearchManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.noblemajesty.marvel.R
import com.noblemajesty.marvel.contracts.MainActivityContract
import com.noblemajesty.marvel.models.getCharacters.Data
import com.noblemajesty.marvel.models.getCharacters.MarvelCharacters
import com.noblemajesty.marvel.models.getCharacters.Result
import com.noblemajesty.marvel.utils.MarvelNetworkCall.getMarvelCharacters
import com.noblemajesty.marvel.utils.SecretUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), MainActivityContract.MainActivityView {
    private var result: List<Result>? = null
    override fun onCreate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBottomNavigation()

        val connectivity = intent.getBooleanExtra("Connectivity", true)

        supportFragmentManager.beginTransaction()
                .add(R.id.frame_layout_main_activity, CharactersFragment(), null)
                .commit()

        if (!connectivity) {
            Snackbar.make(container_main_activity, getString(R.string.main_activity_no_connectivity), Snackbar.LENGTH_INDEFINITE)
                    .setAction("Retry") { getAllMarvelCharacters() }
                    .show()
        } else {
            val intentResult = intent.getBooleanExtra("Exit", false)

            if (intentResult) {
                return finish()
            }

            val allCharactersIntent = intent.getStringExtra("Characters")
            val marvelCharacters = Gson().fromJson(allCharactersIntent, MarvelCharacters::class.java)
            result = (marvelCharacters.data as Data).results

            result!!.forEach {
                Log.e("details", "${it.name} ${it.id} ${it.description}")
            }

            if (intent.action == Intent.ACTION_SEARCH) {
                val query = intent.getStringExtra(SearchManager.QUERY) as String
            }
        }
    }

    private fun getAllMarvelCharacters() {
        try {
            val allMarvelCharacters = async(CommonPool) {
                getMarvelCharacters(SecretUtils.publicKey, SecretUtils.privateKey)
            }

            launch(CommonPool) {
                result = ((allMarvelCharacters.await() as MarvelCharacters).data as Data).results

                result!!.forEach {
                    Log.e("after remaking call", "${it.name} ${it.id} ${it.description}")
                }

            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    private fun setBottomNavigation() {
        val bottomNavigation = bottom_navigation_main_activity
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.marvel_characters -> {
                    toast("Character selected").show()
                    switchToCharactersTab()
                    true }
                R.id.marvel_creators -> {
                    toast("Creators").show()
                    switchToCreatorsTab()
                    true }
                R.id.marvel_stories -> {
                    toast("Stories selected").show()
                    switchToStoriesTab()
                    true }
                R.id.marvel_events -> {
                    toast("Events selected").show()
                    switchToEventsTab()
                    true }

                else -> true
            }
        }
    }

    private fun switchToCharactersTab() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout_main_activity, CharactersFragment(), null)
                .commit()
    }

    private fun switchToStoriesTab() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout_main_activity, StoriesFragment(), null)
                .commit()
    }

    private fun switchToEventsTab() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout_main_activity, EventsFragment(), null)
                .commit()
    }

    private fun switchToCreatorsTab() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout_main_activity, CreatorsFragment(), null)
                .commit()
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
