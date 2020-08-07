package com.example.imagesearchapp.di.module

import android.content.Context
import androidx.room.Room
import com.example.imagesearchapp.data.room.CommentRepositoryImpl
import com.example.imagesearchapp.data.room.db.AppDatabase
import com.example.imagesearchapp.domain.comment.CommentRepository
import com.example.imagesearchapp.utils.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataBaseModule = module {
    single { provideRoomDatabase(androidContext()) }
    single { get<AppDatabase>().photoEntityDao() }
    single<CommentRepository> { CommentRepositoryImpl(get()) }

}

fun provideRoomDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        Constants.DB_NAME
    ).build()
}
