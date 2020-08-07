package com.example.imagesearchapp.data.room

import com.example.imagesearchapp.data.room.db.AppDatabase
import com.example.imagesearchapp.data.room.model.PhotoEntity
import com.example.imagesearchapp.domain.comment.CommentRepository

class CommentRepositoryImpl(private val appDatabase: AppDatabase) :
    CommentRepository {
    override suspend fun getPhotoEntity(id: String): PhotoEntity =
        appDatabase.photoEntityDao().loadPhotoEntityById(id)

    override suspend fun insertAll(photoEntityList: List<PhotoEntity>) =
        appDatabase.photoEntityDao().insertAll(photoEntityList)
}


