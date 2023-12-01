package com.example.vinonovi.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image")
data class Image(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val imageUrl: String,
    val idolGroup: String,
    val idolName: String
)

@Entity(tableName = "gallery_photos")
data class GalleryPhoto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val uri: String // 사진의 Uri를 저장할 필드
)