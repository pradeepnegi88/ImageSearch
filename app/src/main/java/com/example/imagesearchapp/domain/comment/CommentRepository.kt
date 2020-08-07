package com.example.imagesearchapp.domain.comment

import com.example.imagesearchapp.data.room.model.PhotoEntity

interface CommentRepository {
    suspend fun getPhotoEntity(id:String): PhotoEntity

    suspend fun insertAll(users: List<PhotoEntity>)
}
