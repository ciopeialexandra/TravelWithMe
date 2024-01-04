package com.example.myapplication.data

import coil.compose.AsyncImagePainter

data class TripUIState (
    var email: String = "",
    var country: String = "",
    var description: String = "",

    var city: String = "",
    var attractions: String = "",
    var restaurants: String = "",
    var images:List<String> = emptyList(),

    var countryError: Boolean = false,
    var descriptionError: Boolean = false,
    var cityError: Boolean = false,
    var attractionsError: Boolean = false,
    var restaurantsError: Boolean = false
)