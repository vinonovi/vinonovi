package com.example.vinonovi.ui.screen

import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun UploadScreen(navController: NavHostController) {
    var selectUri by remember {
        mutableStateOf<List<Uri>>(emptyList())
    }
    var selectedUri by remember {
        mutableStateOf<List<Uri>>(emptyList())
    }
    val launcher = // 갤러리 이미지 런쳐
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickMultipleVisualMedia(),
            onResult = { uris ->
                if (uris.isNotEmpty()) {
                    selectUri = uris
                }
            }
        )
    LaunchedEffect(selectUri){
        selectedUri += selectUri
        selectedUri = selectedUri.sortedByDescending { uri ->
            uri.lastPathSegment?.toLongOrNull() ?: Long.MIN_VALUE
        }
    }

    Column {
        Button(
            onClick = {
                launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }) {
            Text(text = "이미지 업로드")
        }
        Button(
            onClick = {
                selectedUri = emptyList()
            },
            enabled = selectedUri.isNotEmpty()
        ) {
            Text(text = "전체 삭제")
        }
        LazyVerticalGrid(columns = GridCells.Adaptive(128.dp)) {
            items(selectedUri) { uri ->
                Card(
                ) {
                    GlideImage(
                        modifier = Modifier.size(128.dp),
                        imageModel = { uri },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center
                        ),
                        loading = {
                            Box(modifier = Modifier.matchParentSize()) {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        },
                        failure = {
                            Text(text = "failed")
                        }
                    )
                }
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            selectedUri = selectedUri.filterNot { it == uri }
                        }
                        .graphicsLayer { scaleX = 1f },
                    contentAlignment = Alignment.TopEnd
                ) {
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
                }
            }
        }
    }
}

