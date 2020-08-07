package com.example.imagesearchapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.imagesearchapp.data.room.model.PhotoEntity

@Dao
interface PhotoEntityDao {
    @Query("SELECT * from photoentity where id = :id LIMIT 1")
    suspend fun loadPhotoEntityById(id: String): PhotoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)  // or OnConflictStrategy.IGNORE
    suspend fun insertAll(comments: List<PhotoEntity>)

}