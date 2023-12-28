package com.example.myapplication.navigation


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {
    object SignUpScreen : Screen()
    object SignInScreen : Screen()
    object TermsAndConditionsScreen : Screen()
    object ForgotPasswordScreen : Screen()
    object MainScreen : Screen()
    object AddTripScreen : Screen()
    object ExploreScreen : Screen()
    object ProfileScreen : Screen()
    object TestScreen: Screen()
}

object TravelAppNavigate {
    var currentScreen :MutableState<Screen> = mutableStateOf(Screen.SignUpScreen)

    fun navigateTo(destination: Screen){
        currentScreen.value = destination
    }
}


