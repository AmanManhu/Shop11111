package com.example.shop.data.datasource

import com.example.shop.data.model.ProductDto // Исправлено: используем DTO
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApi {
    @GET("products")
    suspend fun getAllProduct(): List<ProductDto>
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductDto // Исправлено: возвращаем DTO

    companion object {
        private const val BASE_URL = "https://fakestoreapi.com/"

        fun create(): StoreApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build()
                .create(StoreApi::class.java)
        }
    }
}
