package com.noblemajesty.marvel.models.getComics

data class Data(
        val total: Int,
        val count: Int,
        val results: List<Result>
)