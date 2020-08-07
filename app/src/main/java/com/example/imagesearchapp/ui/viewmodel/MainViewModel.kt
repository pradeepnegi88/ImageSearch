package com.example.imagesearchapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagesearchapp.domain.query.QueryUseCase
import com.example.imagesearchapp.domain.query.model.Photo
import com.example.imagesearchapp.utils.NetworkHelper
import com.example.imagesearchapp.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(
    private val queryUseCase: QueryUseCase,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _imageResponse = MutableLiveData<Resource<List<Photo>>>()
    val imageResponse: LiveData<Resource<List<Photo>>>
        get() = _imageResponse

    fun fetchQueryResource(query: String) {
        viewModelScope.launch {
            _imageResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                queryUseCase.getQueryResource(query).let {
                    if (it.isNullOrEmpty()) {
                        _imageResponse.postValue(Resource.error("Something wrong", null))
                    } else _imageResponse.postValue(Resource.success(it))
                }
            } else _imageResponse.postValue(Resource.error("No internet connection", null))
        }
    }
}