package com.example.vinonovi.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
//import com.example.vinonovi.BuildConfig
//import com.example.vinonovi.network.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.Kakao
import kotlinx.coroutines.launch

//@Composable
//fun LoginScreen(navController: NavController) {
//    val scope = rememberCoroutineScope()
//    val supabaseClient = SupabaseClient
//    val client = createSupabaseClient(
//        supabaseUrl = BuildConfig.SUPABASE_URL,
//        supabaseKey = BuildConfig.SUPABASE_ANON_KEY
//    ) {
//        install(GoTrue) {
//            scheme = "http"
//            host = "localhost:3000"
//        }
//    }

//    Column {
//        Button(onClick = {
//            scope.launch {
//                client.gotrue.loginWith(Kakao)
//            }
//        }) {
//            Text("kakao login")
//        }
//    }
//}

