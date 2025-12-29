package com.example.shop.data.mappers

import com.example.shop.data.model.ProductDto
import com.example.shop.data.model.RatingDto
import com.example.shop.domain.model.Product
import com.example.shop.domain.model.Rating

fun ProductDto.toDomain(): Product{
    return Product(
        id = this.id?: -1,
        title = this.title?: "",
        price = this.price?: 0.0,
        description = this.description?: "",
        category = this.category?: "",
        image = this.image?: "",
        rating = this.rating?.toDomain()?: Rating.empty(),
    )
}
fun RatingDto.toDomain(): Rating {
    return Rating(
        count = this.count?: 0,
        rate = this.rate?: 0.0
    )
}