package com.ssa.aholdtest.navigation

import androidx.lifecycle.ViewModelStore

interface NavViewModelStoreProvider {
    fun getViewModelStore(backStackEntryId: String): ViewModelStore
}