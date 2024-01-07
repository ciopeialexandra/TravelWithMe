package com.example.myapplication.data.firebase

data class UserNode(var email: String, var firstName: String, var lastName: String,var story:String) {
    constructor() : this("", "","","")
}