package com.example.imagesearchapp.di.module

import com.example.imagesearchapp.domain.comment.CommentUseCase
import com.example.imagesearchapp.domain.comment.CommentUseCaseImpl
import com.example.imagesearchapp.domain.query.QueryUseCase
import com.example.imagesearchapp.domain.query.QueryUseCaseImpl
import com.example.imagesearchapp.repository.DataBaseRepository
import com.example.imagesearchapp.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(get())
    }
    single {
        DataBaseRepository(get())
    }
    single<QueryUseCase> {
        return@single QueryUseCaseImpl(get())
    }
    single<CommentUseCase> {
        return@single CommentUseCaseImpl(get())
    }
}
