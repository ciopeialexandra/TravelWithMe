package com.example.myapplication

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

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
