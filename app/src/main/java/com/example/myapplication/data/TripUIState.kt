package com.example.myapplication.data

import android.net.Uri

data class TripUIState(
    var email: String = "",
    var country: String = "",
    var description: String = "",

    var city: String = "",
    var attractions: String = "",
    var restaurants: String = "",
    var images: String = "",

    var countryError: Boolean = false,
    var descriptionError: Boolean = false,
    var cityError: Boolean = false,
    var attractionsError: Boolean = false,
    var restaurantsError: Boolean = false
)