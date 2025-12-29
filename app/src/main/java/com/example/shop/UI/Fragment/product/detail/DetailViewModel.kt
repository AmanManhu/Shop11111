package com.example.shop.UI.Fragment.product.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.UI.Fragment.product.models.UIState
import com.example.shop.domain.model.Product
import com.example.shop.domain.Repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    private val _productState = MutableStateFlow<UIState<Product>>(UIState.loading)
    val productState: StateFlow<UIState<Product>> = _productState

    fun loadProductById(id: Int) {
        viewModelScope.launch {
            _productState.value = UIState.loading
            try {
                val product = repository.getProductById(id)
                _productState.value = UIState.Success(product)
            } catch (e: Exception) {
                _productState.value = UIState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
