package com.example.vinonovi.ui.screen

import BlipModelProcessor
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

@Composable
fun SearchScreen(navController: NavHostController) {
    val context = LocalContext.current

    Button(onClick = {
        val ModelProcessor = BlipModelProcessor(context)

//        val output = ModelProcessor.processImage()

        ModelProcessor.close()

    }) {

    }
}

// 앱에 욕심 - ui 꾸미기, caption, blip(q&a), geti
// 앱이 둘 중 하면
// 이미지를 업로드, room 저장, 모델이 room 안에서 image 불러와서 돌리기
// 이미지를
// 이미지를 보여주는 것.

//

//