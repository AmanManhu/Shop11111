package com.example.shop.domain.di

import com.example.shop.domain.usecases.GetProductsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetProductsUseCase(get()) }
}
