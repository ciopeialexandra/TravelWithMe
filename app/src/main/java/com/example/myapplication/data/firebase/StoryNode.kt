package com.example.myapplication.data.firebase

data class StoryNode(
    var email: String,
    var story: String
) {
    constructor() : this("",  "")
}
