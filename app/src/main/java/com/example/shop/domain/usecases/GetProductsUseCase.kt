package com.example.shop.domain.usecases

import com.example.shop.domain.Repository.ProductRepository
import com.example.shop.domain.model.Product

class GetProductsUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): List<Product> { 
        return repository.getProducts()
    }
}
