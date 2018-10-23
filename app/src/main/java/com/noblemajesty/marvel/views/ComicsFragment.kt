package com.noblemajesty.marvel.views


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.noblemajesty.marvel.R
import com.noblemajesty.marvel.contracts.MainActivityContract
import com.noblemajesty.marvel.models.getCharacters.MarvelCharacters
import com.noblemajesty.marvel.models.getComics.MarvelComics
import com.noblemajesty.marvel.presenters.MainActivityPresenter


/**
 * A simple [Fragment] subclass.
 * Use the [StoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ComicsFragment : Fragment(), MainActivityContract.MainActivityView {
    private val mainActivityPresenter = MainActivityPresenter (this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        getAllComics()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stories, container, false)
    }

    private fun getAllComics() = mainActivityPresenter.getAllComics()


    override fun onGetAllMarvelCharacterSuccess(marvelCharacters: MarvelCharacters?) { }

    override fun onGetAllMarvelCharacterError() { }

    override fun onGetMarvelComicsSuccess(marvelComics: MarvelComics?) {
        Log.e("Herrrrrrreee", "Hrreeeeeeeeeeeeeeeeeeeeeee")
        marvelComics?.let { it -> Log.e("Comics>>>>", "${it.data.results}") }
    }

    override fun onGetMarvelComicsError() {
        (activity!! as? MainActivity)!!
                .displayErrorSnackbar(::getAllComics)
    }

}
