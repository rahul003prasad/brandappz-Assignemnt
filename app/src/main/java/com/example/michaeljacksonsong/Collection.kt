package com.example.michaeljacksonsong

import java.io.Serializable

data class Collection(
    val trackName: String,
    val primaryGenreName: String,
    val artistName: String,
    val collectionName: String,
    val image: String

) : Serializable