package com.example.imagesearchapp.domain.comment

import com.example.imagesearchapp.domain.comment.model.Comment

interface CommentUseCase {
    suspend fun getPhotoEntity(id:String): Comment
    suspend fun insertAll(comments: List<Comment>)
}