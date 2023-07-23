package com.ssa.aholdtest

import com.ssa.aholdtest.artobjects.ItemsViewModel
import com.ssa.aholdtest.details.ArtObjectsDetailsViewModel
import com.ssa.aholdtest.navigation.NavViewModelStore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NavViewModelStore() }
    viewModel { ItemsViewModel(get()) }
    viewModel { ArtObjectsDetailsViewModel(get()) }
}