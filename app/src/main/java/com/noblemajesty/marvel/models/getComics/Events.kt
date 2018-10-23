package com.noblemajesty.marvel.models.getComics

data class Events(
        val available: Int,
        val collectionURI: String,
        val items: List<Any>,
        val returned: Int
)