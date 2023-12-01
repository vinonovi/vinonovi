package com.example.vinonovi

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinonovi.data.AppDatabase
import com.example.vinonovi.data.GalleryPhoto
import com.example.vinonovi.data.GalleryPhotoDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.vinonovi", appContext.packageName)
    }
}

@RunWith(AndroidJUnit4::class)
class DatabaseInstrumentedTest {

    private lateinit var galleryPhotoDao: GalleryPhotoDao
    private lateinit var db: AppDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        galleryPhotoDao = db.galleryPhotoDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndRetrieveData() = runBlocking {
        // Given
        val expectedUri = "content://image/1" // 예상되는 Uri
        val galleryPhoto = GalleryPhoto(uri = expectedUri)

        // When
        galleryPhotoDao.insertPhoto(galleryPhoto)

        // Then
        val photos = galleryPhotoDao.getAllPhotos().first()
        assertThat(photos.size, `is`(1)) // 데이터베이스에 저장된 데이터 개수 확인
        assertThat(photos[0].uri, `is`(expectedUri)) // 저장된 데이터의 Uri 확인
    }
}