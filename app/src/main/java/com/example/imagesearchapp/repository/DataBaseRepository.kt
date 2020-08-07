package com.example.imagesearchapp.repository

import com.example.imagesearchapp.domain.comment.CommentRepository
import com.example.imagesearchapp.data.room.model.PhotoEntity

class DataBaseRepository(private val databaseHelper: CommentRepository) {
    suspend fun getPhotoEntity(id:String): PhotoEntity =  databaseHelper.getPhotoEntity(id)
    suspend fun insertAll(photoEntityList:List<PhotoEntity>) =  databaseHelper.insertAll(photoEntityList)
}