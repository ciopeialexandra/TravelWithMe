package com.example.myapplication.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.myapplication.components.BottomBar
import com.example.myapplication.data.SignUpViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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



