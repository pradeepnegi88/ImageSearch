package com.example.imagesearchapp.ui.viewmodel

import com.example.imagesearchapp.domain.comment.CommentUseCase
import com.example.imagesearchapp.domain.comment.model.Comment
import com.example.imagesearchapp.utils.NetworkHelper
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class ImageDescriptionViewModelTest {
    private val dispatcher = TestCoroutineDispatcher()
    private lateinit var commentUseCase: CommentUseCase
    private lateinit var networkHelper: NetworkHelper
    private lateinit var imageDescriptionViewModel: ImageDescriptionViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        commentUseCase = mockk(relaxed = true)
        networkHelper = mockk(relaxed = true)
        imageDescriptionViewModel = ImageDescriptionViewModel(commentUseCase, networkHelper)
        every { networkHelper.isNetworkConnected() }.returns(true)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `itShouldInsert`()  = runBlockingTest{
        val insertComment = listOf(Comment("1", "comment"))
        imageDescriptionViewModel.insertComment(insertComment)
        coVerify(exactly = 1) { commentUseCase.insertAll(any()) }
    }

    @Test
    fun `itShouldFetchStoredComments`()  = runBlockingTest{
        imageDescriptionViewModel.fetchStoredComments("abc")
        coVerify(exactly = 1) { commentUseCase.getPhotoEntity(any()) }
    }

}