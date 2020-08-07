package com.example.imagesearchapp.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.imagesearchapp.data.room.PhotoEntityDao
import com.example.imagesearchapp.data.room.model.PhotoEntity

@Database(entities = [PhotoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoEntityDao(): PhotoEntityDao

}