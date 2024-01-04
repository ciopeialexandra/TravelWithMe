package com.example.myapplication
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen (
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Explore: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Outlined.Explore
    )

    object Story: BottomBarScreen(
        route = "story",
        title = "Story",
        icon = Icons.Filled.Image
    )
    object Add: BottomBarScreen(
        route = "add",
        title = "Add",
        icon = Icons.Filled.AddCircle
    )
    object Search: BottomBarScreen(
        route = "search",
        title = "Search",
        icon = Icons.Filled.Search
    )
    object Profile: BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Filled.Person
    )

}
