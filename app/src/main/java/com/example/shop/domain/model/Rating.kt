package com.example.shop.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class Rating(
    val count: Int?,
    val rate: Double?
)
{
    companion object{
        fun empty(): Rating{
            return Rating(
                count = 0,
                rate = 0.0,
            )
        }
    }
}
