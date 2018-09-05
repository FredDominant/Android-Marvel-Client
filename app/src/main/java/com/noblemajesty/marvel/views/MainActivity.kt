package com.noblemajesty.marvel.views

import android.app.SearchManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.noblemajesty.marvel.R
import com.noblemajesty.marvel.contracts.MainActivityContract
import com.noblemajesty.marvel.presenters.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*
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
                    toast("Creator selected").show()
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
