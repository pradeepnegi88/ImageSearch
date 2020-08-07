package com.example.imagesearchapp.domain.comment

import com.example.imagesearchapp.data.room.model.PhotoEntity
import com.example.imagesearchapp.domain.comment.model.Comment
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CommentUseCaseImplTest {
    private lateinit var commentUseCaseImpl: CommentUseCaseImpl
    private lateinit var commentRepository: CommentRepository

    @Before
    fun setup() {
        commentRepository = mockk(relaxed = true)
        commentUseCaseImpl = CommentUseCaseImpl(commentRepository)
    }

    @Test
    fun `itShouldGetPhoto`() = runBlocking {
        val photoEntity = PhotoEntity("1", "comment1")
        coEvery { commentRepository.getPhotoEntity("1") }.returns(photoEntity)
        val dataResponse = async {  commentUseCaseImpl.getPhotoEntity("1")}
        assertThat("1", CoreMatchers.equalTo(dataResponse.await().id))
        assertThat("comment1", CoreMatchers.equalTo(dataResponse.await().comment))
    }

    @Test
    fun `itShouldInsert`() = runBlocking {
        val commentList = listOf<Comment>(Comment("1", "comment1"))
        val dataResponse = async {  commentUseCaseImpl.insertAll(commentList)}
       coVerify(exactly = 1) { commentRepository.insertAll(any()) }
    }

}