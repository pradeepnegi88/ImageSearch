package com.example.imagesearchapp.domain.query

import com.example.imagesearchapp.data.api.model.ImageResponse
import retrofit2.Response

interface QueryRepository {
    suspend fun getQueryResource(query:String): Response<ImageResponse>
}