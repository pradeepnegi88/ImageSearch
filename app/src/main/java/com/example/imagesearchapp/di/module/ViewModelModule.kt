package com.example.imagesearchapp.di.module

import com.example.imagesearchapp.ui.viewmodel.ImageDescriptionViewModel
import com.example.imagesearchapp.ui.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get(),get())
    }
    viewModel {
        ImageDescriptionViewModel(get(),get())
    }
}