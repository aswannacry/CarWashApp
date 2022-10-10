package com.securiforce.carwash

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val route: String, val label: String, val icons: ImageVector){

    object Home: NavigationItem("home", "Home", Icons.Default.Home)
    object ListCar: NavigationItem("list_car", "Check Car", Icons.Default.List)
}
