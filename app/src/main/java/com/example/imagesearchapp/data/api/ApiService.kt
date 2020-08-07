package com.example.imagesearchapp.data.api

import com.example.imagesearchapp.data.api.model.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/1")
    suspend fun getUsers(@Query("q") q: String): Response<ImageResponse>

}