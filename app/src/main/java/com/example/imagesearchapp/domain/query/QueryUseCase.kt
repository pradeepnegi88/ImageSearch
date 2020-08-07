package com.example.imagesearchapp.domain.query

import com.example.imagesearchapp.domain.query.model.Photo
import retrofit2.Response

interface QueryUseCase {
    suspend fun getQueryResource(query:String): List<Photo>?
}