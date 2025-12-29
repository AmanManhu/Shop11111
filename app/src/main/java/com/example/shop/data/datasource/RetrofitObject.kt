package com.example.shop.data.datasource

import com.example.shop.data.datasource.StoreApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitObject {
    private const val BASE_URL = "https://fakestoreapi.com/"

    private val loggin = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggin).build()

    private val contentType = "application/json".toMediaType()

    val api: StoreApi by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build().create(StoreApi::class.java)
    }
}