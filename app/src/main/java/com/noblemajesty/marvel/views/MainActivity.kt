package com.noblemajesty.marvel.views

import android.app.SearchManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.noblemajesty.marvel.R
import com.noblemajesty.marvel.contracts.MainActivityContract
import com.noblemajesty.marvel.models.getCharacters.Data
import com.noblemajesty.marvel.models.getCharacters.MarvelCharacters
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), MainActivityContract.MainActivityView {
    override fun onCreate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.frame_layout_main_activity, CharactersFragment(), null)
            .commit()

        val intentResult = intent.getBooleanExtra("Exit", false)

        val allCharactersIntent = intent.getStringExtra("Characters")

        val marvelCharacters = Gson().fromJson(allCharactersIntent, MarvelCharacters::class.java)

        val result = (marvelCharacters.data as Data).results

        result!!.forEach {
            Log.e("details", "${it.name} ${it.id} ${it.description}")
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
