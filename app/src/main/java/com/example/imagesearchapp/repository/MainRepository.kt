package com.example.imagesearchapp.repository

import com.example.imagesearchapp.domain.query.QueryRepository

class MainRepository(private val queryRepository: QueryRepository) {
    suspend fun getQueryResource(query: String) = queryRepository.getQueryResource(query)
}