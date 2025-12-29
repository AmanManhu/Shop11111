package com.example.shop.data.di

import com.example.shop.data.datasource.StoreApi
import com.example.shop.data.repository.ProductRepositoryImpl
import com.example.shop.domain.Repository.ProductRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val dataModule = module {

    // 1. Создаем Retrofit один раз
    single<Retrofit> {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val json = Json {
            ignoreUnknownKeys = true
        }

        Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    // 2. Создаем API, используя готовый Retrofit
    single<StoreApi> {
        get<Retrofit>().create(StoreApi::class.java)
    }

    // 3. Создаем Репозиторий, используя готовый API
    single<ProductRepository> {
        ProductRepositoryImpl(api = get())
    }

}
