package com.example.imagesearchapp.domain.comment

import com.example.imagesearchapp.data.room.model.PhotoEntity
import com.example.imagesearchapp.domain.comment.model.Comment

class CommentUseCaseImpl(private val commentRepository: CommentRepository) : CommentUseCase {
    override suspend fun getPhotoEntity(id: String): Comment {
        val data = commentRepository.getPhotoEntity(id)
        if (data != null) {
            return Comment(data.id, data.comment)
        }
        return data
    }

    override suspend fun insertAll(comments: List<Comment>) {
        val photoEntityList = comments.map { PhotoEntity(it.id, it.comment) }.toList()
        commentRepository.insertAll(photoEntityList)
    }
}