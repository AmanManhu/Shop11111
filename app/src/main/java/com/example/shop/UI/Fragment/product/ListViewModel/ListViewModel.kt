package com.example.shop.UI.Fragment.product.ListViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.UI.Fragment.product.models.UIState
import com.example.shop.domain.model.Product
import com.example.shop.domain.usecases.GetProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val getProductsUseCase: GetProductsUseCase
): ViewModel() {

    private val _state = MutableStateFlow<UIState<List<Product>>>(UIState.loading)
    val state: StateFlow<UIState<List<Product>>> = _state
    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            _state.value = UIState.loading
            try {
                val result = getProductsUseCase.invoke()
                _state.value = UIState.Success(result)
            } catch (e: java.lang.Exception) {
                _state.value = UIState.Error(e.message.toString())
            }
        }
    }
}
