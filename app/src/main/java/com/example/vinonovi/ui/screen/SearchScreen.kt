package com.example.vinonovi.ui.screen

import android.app.Application
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vinonovi.controller.GalleryViewModel
import kotlinx.coroutines.launch
import java.net.URLEncoder

@Composable
fun SearchScreen(navController: NavHostController) {
    val context = LocalContext.current
    val selectedImages = remember { mutableStateListOf<Uri>() }
    val scope = rememberCoroutineScope()
    val findMyIdolList by remember { mutableStateOf<MutableList<Int>>(mutableListOf()) }
    var searchText by remember { mutableStateOf("") }

    // gallery에서 user가 image 선택한 만큼 가져오는 코드
    val multiPhotoLoader = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        // 백그라운드 스레드에서 실행되는 코드
        onResult = { uriList ->
            scope.launch {
                uriList.forEach { uri ->
                    selectedImages.add(uri)
                }
            }
        }
    )

    Column {
        SearchField(
            text = searchText,
            onTextChanged = { newText ->
                searchText = newText
            }
        )

        Button(
            onClick = {
                multiPhotoLoader.launch(null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(10.dp)
                .border(
                    1.dp,
                    Color(android.graphics.Color.parseColor("#e39368")),
                    shape = RoundedCornerShape(10)
                ),
            colors = ButtonDefaults.buttonColors(
                Color(0xFFF7B14B),
                contentColor = White
            ),
            shape = RoundedCornerShape(10)

        ) {
            Text(
                text = "검색하기",
                textAlign = TextAlign.Center,
                color = White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun SearchField(text: String, onTextChanged: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            text = newText
            // 여기에 새로운 텍스트로 수행할 작업을 추가할 수 있습니다.
        },
        label = { Text("검색어를 입력하세요") }, // 텍스트 필드 레이블
        modifier = Modifier
            .padding(10.dp) // 필드 주위의 패딩
            .fillMaxWidth() // 필드의 너비를 최대로 확장
    )
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(navController = rememberNavController())
}