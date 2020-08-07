package com.example.imagesearchapp.domain.query

import com.example.imagesearchapp.domain.query.model.Photo

class QueryUseCaseImpl(private val queryRepository: QueryRepository) : QueryUseCase {
    override suspend fun getQueryResource(query: String): List<Photo>? {
        val dataResponse = queryRepository.getQueryResource(query)
        return if (dataResponse.isSuccessful)
            dataResponse.body()?.data?.asSequence()?.filter { it.images != null }
                ?.map { Photo(it.images[0].id, it.title, it.images[0].link) }?.toList()
        else
            emptyList()
    }
}