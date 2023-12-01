package com.example.vinonovi.data

import androidx.room.Database
import androidx.room.RoomDatabase

//@Database(entities = [Image::class], version = 1)
//abstract class ImageDatabase {
//}

@Database(entities = [GalleryPhoto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun galleryPhotoDao(): GalleryPhotoDao
}