package com.example.vinonovi.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Gallery : Screen("gallery")
    object Upload : Screen("upload")
    object Search : Screen("search")
}