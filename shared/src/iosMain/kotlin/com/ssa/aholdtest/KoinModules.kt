package com.ssa.aholdtest

import com.ssa.aholdtest.artobjects.ItemsViewModel
import com.ssa.aholdtest.details.ArtObjectsDetailsViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { ItemsViewModel(get()) }
    factory { ArtObjectsDetailsViewModel(get()) }
}