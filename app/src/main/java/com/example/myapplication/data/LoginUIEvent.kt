package com.example.myapplication.data

sealed class LoginUIEvent {
    data class EmailChanged(val email: String) : LoginUIEvent()
    data class PasswordChanged(val password: String) : LoginUIEvent()
    data class StoryAdded(val story: String) : LoginUIEvent()

    object LoginButtonClicked : LoginUIEvent()
}