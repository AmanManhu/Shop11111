package com.example.shop.UI.Fragment.product.models

sealed interface UIState<out T> {
    data object loading: UIState<Nothing>
    data class Error(val message: String) : UIState<Nothing>
    data class Success<T>(val data: T): UIState<T>
}