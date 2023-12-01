package com.example.vinonovi.network

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.vinonovi.BuildConfig
import com.example.vinonovi.navigation.Screen
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.appleNativeLogin
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.rememberLoginWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.Kakao

object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = BuildConfig.SUPABASE_URL,
        supabaseKey = BuildConfig.SUPABASE_ANON_KEY
    ) {
        //GoTrue 설치 시 android & ios는 딥 링크 필요
        install(GoTrue) {
            scheme = "http"
            host = "localhost:3000"
        }
//        install(ComposeAuth) {
//            googleNativeLogin(serverClientId = "google-client-id")
//            appleNativeLogin()
//        }
    }

    suspend fun signInWithKakao() {
        client.gotrue.loginWith(Kakao)
    }

    suspend fun signOut() {
        client.gotrue.logout()
    }
    // 이거와 아래 차이? 이거는 gotrue 모듈을 통해 간소화?
    suspend fun signInWithGoogle() {
        client.gotrue.loginWith(Google)
    }

    @Composable // rememberLoginWithGoogle 때문에 composable 함수 안에서
    fun signInWithGoogle(navcontroller: NavController) {
        val action = client.composeAuth.rememberLoginWithGoogle(
            onResult = { result -> //optional error handling
                when (result) {
                    is NativeSignInResult.Success -> {
                        navcontroller.navigate(Screen.Gallery.route)
                    }
                    is NativeSignInResult.ClosedByUser -> {}
                    is NativeSignInResult.Error -> {}
                    is NativeSignInResult.NetworkError -> {}
                }
            },
            fallback = {
                // optional: add custom error handling, not required by default
            }
        )
        // 이 부분 ui에서 쓰기
//        Button(
//            onClick = {
//                // Google 로그인 액션 시작
//                action.startFlow()
//            }
//        ) {
//            Text("Google Login")
//        }
    }
}