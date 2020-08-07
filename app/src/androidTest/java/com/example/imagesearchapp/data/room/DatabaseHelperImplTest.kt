package com.example.imagesearchapp.data.room

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.imagesearchapp.data.room.db.AppDatabase
import com.example.imagesearchapp.data.room.model.PhotoEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseHelperImplTest {
    private val photoEntity = PhotoEntity("xyz", "Comment")
    private lateinit var appDatabase: AppDatabase
    private lateinit var photoEntityDao: PhotoEntityDao
    private val context = InstrumentationRegistry.getInstrumentation().context

    @Before
    fun setup() {
        appDatabase =
            Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries()
                .build()
        photoEntityDao = appDatabase.photoEntityDao()
    }

    @After
    fun teardown() {
        appDatabase.close()
    }

    @Test
    fun `itShouldInsertComment`() = runBlocking {
        photoEntityDao.insertAll(arrayListOf(photoEntity))
        val photoEntityResponse = photoEntityDao.loadPhotoEntityById("xyz")
        assertNotNull(photoEntityResponse)
        assertTrue("Comment" == photoEntityResponse.comment)
    }

    @Test
    fun `itShouldGetComment`() = runBlocking {
        val photoEntityResponseBefore = photoEntityDao.loadPhotoEntityById("xyz")
        assertNull(photoEntityResponseBefore)
        photoEntityDao.insertAll(arrayListOf(photoEntity))
        val photoEntityResponseAfter = photoEntityDao.loadPhotoEntityById("xyz")
        assertNotNull(photoEntityResponseAfter)
        assertTrue("Comment" == photoEntityResponseAfter.comment)
        assertTrue("xyz" == photoEntityResponseAfter.id)
    }

}
