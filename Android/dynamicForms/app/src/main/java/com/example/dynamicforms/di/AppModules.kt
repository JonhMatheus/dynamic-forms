package com.example.dynamicforms.di

import com.example.dynamicforms.data.repositories.FormRepository
import com.example.dynamicforms.viewModels.FormViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RetrofitInstance.api }
    single { FormRepository(get()) }
    viewModel { FormViewModel(get()) }
}