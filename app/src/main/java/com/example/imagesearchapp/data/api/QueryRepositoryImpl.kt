package com.example.imagesearchapp.data.api

import com.example.imagesearchapp.data.api.model.ImageResponse
import com.example.imagesearchapp.domain.query.QueryRepository
import retrofit2.Response

class QueryRepositoryImpl(private val apiService: ApiService) :
    QueryRepository {
    override suspend fun getQueryResource(query: String): Response<ImageResponse> = apiService.getUsers(query)

}