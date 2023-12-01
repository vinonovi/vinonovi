package com.example.vinonovi.controller

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.vinonovi.data.AppDatabase
import com.example.vinonovi.data.GalleryPhoto
import com.example.vinonovi.data.GalleryPhotoDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private val galleryPhotoDao: GalleryPhotoDao

    init {
        val database = Room.databaseBuilder(
            application,
            AppDatabase::class.java, "gallery_database"
        ).build()

        galleryPhotoDao = database.galleryPhotoDao()
    }

    fun savePhoto(uri: String) {
        viewModelScope.launch {
            galleryPhotoDao.insertPhoto(GalleryPhoto(uri = uri))
        }
    }

    fun getAllPhotos(): Flow<List<GalleryPhoto>> {
        return galleryPhotoDao.getAllPhotos()
    }
}