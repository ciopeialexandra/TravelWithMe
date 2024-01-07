package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.data.LoginViewModel
import com.example.myapplication.data.TripViewModel
import com.example.myapplication.data.firebase.FirebaseTripStore
import com.example.myapplication.data.firebase.FirebaseUserStore
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.TravelAppNavigate
import com.example.myapplication.screens.AddTripScreen
import com.example.myapplication.screens.ExploreScreen
import com.example.myapplication.screens.ProfileScreen
import com.example.myapplication.screens.SearchScreen
import com.example.myapplication.screens.StoryScreen

lateinit var viewModel3: TripViewModel
lateinit var viewModel2: LoginViewModel
@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(
        navController = navController ,
        startDestination = BottomBarScreen.Explore.route
    ){
        composable(route = BottomBarScreen.Explore.route){
            TravelAppNavigate.navigateTo(Screen.ExploreScreen)
        }
        composable(route = BottomBarScreen.Story.route){
            viewModel2 = LoginViewModel(FirebaseUserStore())
            StoryScreen(viewModel2)
        }
        composable(route = BottomBarScreen.Add.route){
            viewModel3  = TripViewModel(FirebaseTripStore())
            AddTripScreen(viewModel3)
        }
        composable(route = BottomBarScreen.Search.route){
            viewModel3  = TripViewModel(FirebaseTripStore())
            SearchScreen(viewModel3)
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen()
        }
    }
}


