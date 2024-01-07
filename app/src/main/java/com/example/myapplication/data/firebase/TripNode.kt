package com.example.myapplication.data.firebase


data class TripNode(
    var email: String,
    var country: String,
    var description: String,
    var city: String,
    var attractions: String,
    var restaurants: String,
    var images: String
) {
    constructor() : this("", "","","","","", "")
}