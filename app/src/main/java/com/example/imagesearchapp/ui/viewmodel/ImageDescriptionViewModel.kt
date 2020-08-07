package com.example.imagesearchapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagesearchapp.domain.comment.CommentUseCase
import com.example.imagesearchapp.utils.NetworkHelper
import com.example.imagesearchapp.utils.Resource
import kotlinx.coroutines.launch

class ImageDescriptionViewModel(
    private val commentUseCase: CommentUseCase,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _photoEntity =
        MutableLiveData<Resource<com.example.imagesearchapp.domain.comment.model.Comment>>()
    val photoEntity: LiveData<Resource<com.example.imagesearchapp.domain.comment.model.Comment>>
        get() = _photoEntity


    fun fetchStoredComments(imageId: String) {
        viewModelScope.launch {
            _photoEntity.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                commentUseCase.getPhotoEntity(imageId).let {
                    if (it != null)
                        _photoEntity.postValue(Resource.success(it))
                }
            } else internetFailure()
        }
    }


    fun insertComment(photoEntityList: List<com.example.imagesearchapp.domain.comment.model.Comment>) {
        viewModelScope.launch {
            _photoEntity.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                commentUseCase.insertAll(photoEntityList)
            } else internetFailure()
        }
    }

    private fun internetFailure() {
        _photoEntity.postValue(Resource.error("No internet connection", null))
    }
}