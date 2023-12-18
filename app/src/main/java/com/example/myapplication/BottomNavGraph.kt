package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.TravelAppNavigate
import com.example.myapplication.screens.AddTripScreen
import com.example.myapplication.screens.ExploreScreen

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

        }
        composable(route = BottomBarScreen.Add.route){
            TravelAppNavigate.navigateTo(Screen.AddTripScreen)
        }
        composable(route = BottomBarScreen.Search.route){

        }
        composable(route = BottomBarScreen.Profile.route){

        }
    }
}
