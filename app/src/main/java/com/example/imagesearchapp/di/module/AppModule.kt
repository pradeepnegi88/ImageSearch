package com.example.imagesearchapp.di.module

import android.content.Context
import com.example.imagesearchapp.BuildConfig.BASE_URL
import com.example.imagesearchapp.data.api.ApiService
import com.example.imagesearchapp.utils.NetworkHelper
import com.example.imagesearchapp.data.api.QueryRepositoryImpl
import com.example.imagesearchapp.domain.query.QueryRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single { provideOkHttpClient() }
    single {
        provideRetrofit(
            get(),
            BASE_URL
        )
    }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }

    single<QueryRepository> {
        return@single QueryRepositoryImpl(get())
    }
}

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

private fun provideOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
   return OkHttpClient.Builder().addInterceptor { chain ->
        val request =
            chain.request().newBuilder().addHeader("Authorization", "Client-ID 137cda6b5008a7c")
                .build()
        chain.proceed(request)
    }.addInterceptor(loggingInterceptor)
        .build()
}

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)
