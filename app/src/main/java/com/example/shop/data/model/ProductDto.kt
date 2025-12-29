package com.example.shop.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ProductDto(
    @SerialName("category")
    val category: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("image")
    val image: String?,
    @SerialName("price")
    val price: Double?,
    @SerialName("rating")
    val rating: RatingDto?,
    @SerialName("title")
    val title: String?
)
