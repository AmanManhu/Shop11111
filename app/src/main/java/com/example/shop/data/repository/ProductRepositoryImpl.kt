package com.example.shop.data.repository

import com.example.shop.data.datasource.StoreApi
import com.example.shop.data.mappers.toDomain
import com.example.shop.domain.model.Product
import com.example.shop.domain.Repository.ProductRepository

class ProductRepositoryImpl(
    private val api: StoreApi
) : ProductRepository {
    override suspend fun getProducts(): List<Product> {
        val data = api.getAllProduct()
        return data.map { it.toDomain() }
    }

    override suspend fun getProductById(id: Int): Product {
        val data = api.getProductById(id)
        return data.toDomain()
    }
}
