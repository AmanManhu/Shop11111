package com.example.shop.UI.di

import com.example.shop.UI.Fragment.product.ListViewModel.ListViewModel
import com.example.shop.UI.Fragment.product.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { ListViewModel(get()) }
    // ДОБАВЛЕНО: Инструкция для создания DetailViewModel
    viewModel { DetailViewModel(get()) }
}
