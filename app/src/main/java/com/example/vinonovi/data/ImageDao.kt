package com.example.vinonovi.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface ImageDao {
}

@Dao
interface GalleryPhotoDao {
    @Insert
    suspend fun insertPhoto(photo: GalleryPhoto)

    @Query("SELECT * FROM gallery_photos")
    fun getAllPhotos(): Flow<List<GalleryPhoto>>
}