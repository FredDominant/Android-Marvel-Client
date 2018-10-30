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
import com.noblemajesty.marvel.models.getCharacters.MarvelCharacters
import com.noblemajesty.marvel.models.getCharacters.Result
import com.noblemajesty.marvel.models.getComics.MarvelComics
import com.noblemajesty.marvel.presenters.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), MainActivityContract.MainActivityView {

    private val mainActivityPresenter = MainActivityPresenter(this, this)
    private var result: List<Result> = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("onCreate", "called again")
        setBottomNavigation()

        val connectivity = intent.getBooleanExtra("Connectivity", true)

        supportFragmentManager.beginTransaction()
                .add(R.id.frame_layout_main_activity, CharactersFragment(), null)
                .commit()

        if (!connectivity) {
            displayErrorSnackbar(::doNothing)
        } else {
            val intentResult = intent.getBooleanExtra("Exit", false)

            if (intentResult) return finish()

            if (intent.action == Intent.ACTION_SEARCH) { handleSearch() }

            val allCharactersIntent : String? = intent.getStringExtra("Characters")
            allCharactersIntent.let { _ ->
                val marvelCharacters : MarvelCharacters? = Gson()
                        .fromJson(allCharactersIntent, MarvelCharacters::class.java)
                marvelCharacters?.let { it ->
                    result = it.data?.results ?: return@let
                    result.forEach {
                        Log.e("details", "${it.name} ${it.id} ${it.description}")
                    }
                }
            }
        }
    }

    fun displayErrorSnackbar(callback: () -> Unit?) {
        Snackbar.make(container_main_activity,
                getString(R.string.main_activity_no_connectivity),
                Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry") { callback() }
                .show()
    }
    private fun getAllMarvelCharacters() {
        mainActivityPresenter.getAllMarvelCharacters()
//        val allMarvelCharacters = async(CommonPool) {
//            getMarvelCharacters(SecretUtils.publicKey, SecretUtils.privateKey)
//        }
//
//        launch(CommonPool) {
//            try {
//                result = allMarvelCharacters.await()?.data?.results ?: return@launch
//                result.forEach {
//                    Log.e("after remaking call", "${it.name} ${it.id} ${it.description}")
//                }
//            } catch(error: Exception) {
//                Log.e("Error in MainActivity", error.message)
//                displayErrorSnackbar()
//            }
//        }
    }

    private fun getMarvelComics() = mainActivityPresenter.getAllComics()

    override fun onGetAllMarvelCharacterSuccess(marvelCharacters: MarvelCharacters?) {
        marvelCharacters?.let { it -> Log.e("Characters", it.toJson().toString()) }
    }
    override fun onGetMarvelComicsSuccess(marvelComics: MarvelComics?) {
        marvelComics?.let {
        }
    }

    override fun onGetMarvelComicsError(it: Throwable) = displayErrorSnackbar(::getMarvelComics)

    override fun onGetAllMarvelCharacterError() = displayErrorSnackbar(::getAllMarvelCharacters)

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
                R.id.marvel_comics -> {
                    toast("Comics selected").show()
                    switchToComicsTab()
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

    private fun switchToComicsTab() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout_main_activity, ComicsFragment(), null)
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
            R.id.action_search_marvel -> { onSearchRequested() }
            else -> true
        }
    }

    private fun handleSearch() {
        val query = intent.getStringExtra(SearchManager.QUERY) as String
        toast(query).show()
    }

    override fun onBackPressed() {
        val intent = Intent(this@MainActivity, SplashActivity::class.java)
        intent.putExtra("Exit", true)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        return super.onBackPressed()
    }

    override fun onStop() {
        super.onStop()
        mainActivityPresenter.onStop()
    }

    private fun doNothing() { Log.e("Error", "Error") }
}
