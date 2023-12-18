package com.example.myapplication.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myapplication.screens.AddTripScreen
import com.example.myapplication.screens.ExploreScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screen {
    object SignUpScreen : Screen()
    object SignInScreen : Screen()
    object TermsAndConditionsScreen : Screen()
    object ForgotPasswordScreen : Screen()
}

object TravelAppNavigate {
    var currentScreen :MutableState<Screen> = mutableStateOf(Screen.SignUpScreen)

    fun navigateTo(destination: Screen){
        currentScreen.value = destination
    }

}
@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(
        navController = navController ,
        startDestination = BottomBarScreen.Explore.route
    ){
        composable(route = BottomBarScreen.Explore.route){
            ExploreScreen()
        }
        composable(route = BottomBarScreen.Story.route){

        }
        composable(route = BottomBarScreen.Add.route){
            AddTripScreen()
        }
        composable(route = BottomBarScreen.Search.route){

        }
        composable(route = BottomBarScreen.Profile.route){

        }
    }
}
sealed class BottomBarScreen (
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Explore: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Explore
    )

    object Story: BottomBarScreen(
        route = "story",
        title = "Story",
        icon = Icons.Default.Image
    )
    object Add: BottomBarScreen(
        route = "add",
        title = "Add",
        icon = Icons.Default.AddCircle
    )
    object Search: BottomBarScreen(
        route = "search",
        title = "Search",
        icon = Icons.Default.Search
    )
    object Profile: BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )

}