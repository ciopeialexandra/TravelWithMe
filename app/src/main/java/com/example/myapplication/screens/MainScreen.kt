package com.example.myapplication.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.BottomBarScreen
import com.example.myapplication.BottomNavGraph
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun MainScreen(){
//    Surface (
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(28.dp)
//            .background(color = Color.Black)
//    ){
        val navController = rememberNavController()

        Scaffold (
            bottomBar = { BottomBar(navController = navController)}
        ){
            BottomNavGraph(navController = navController)
        }
    }
//}

@Composable
fun BottomBar(navController : NavHostController){
    val screens = listOf(
        BottomBarScreen.Explore,
        BottomBarScreen.Story,
        BottomBarScreen.Add,
        BottomBarScreen.Search,
        BottomBarScreen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation{
        screens.forEach{ screen ->
            AddItem(screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
){
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(imageVector = screen.icon,
                contentDescription = "Navigation Icon")
        },
        selected = currentDestination?.hierarchy?.any{
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route)
        }
    )
}


