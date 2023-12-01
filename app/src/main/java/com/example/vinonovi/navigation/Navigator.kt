package com.example.vinonovi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vinonovi.ui.screen.GalleryScreen
import com.example.vinonovi.ui.screen.LoginScreen
import com.example.vinonovi.ui.screen.SearchScreen
import com.example.vinonovi.ui.screen.UploadScreen

@Composable
fun Navigator() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Gallery.route) {
        composable(Screen.Login.route) { LoginScreen(navController) } // 완성되면 사용 예정
        composable(Screen.Gallery.route) { GalleryScreen(navController) }
        composable(Screen.Upload.route) { UploadScreen(navController) }
        composable(Screen.Search.route) { SearchScreen(navController) }
    }
}