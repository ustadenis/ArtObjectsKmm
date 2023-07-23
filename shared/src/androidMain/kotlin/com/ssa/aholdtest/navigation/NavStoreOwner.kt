package com.ssa.aholdtest.navigation

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class NavStoreOwner : ViewModelStoreOwner {

    override val viewModelStore: ViewModelStore by lazy { ViewModelStore() }

}