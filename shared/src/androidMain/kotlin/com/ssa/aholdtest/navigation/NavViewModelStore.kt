package com.ssa.aholdtest.navigation

import androidx.lifecycle.ViewModelStore
import com.rickclephas.kmm.viewmodel.KMMViewModel
import kotlin.collections.set

class NavViewModelStore : KMMViewModel(), NavViewModelStoreProvider {

    private val viewModelStores = mutableMapOf<String, NavStoreOwner>()

    fun clear(backStackEntryId: String) {
        // Clear and remove the NavGraph's ViewModelStore
        val viewModelStore = viewModelStores.remove(backStackEntryId)
        viewModelStore?.viewModelStore?.clear()
    }

    fun getStoreOwner(backStackEntryId: String): NavStoreOwner {
        var viewModelStoreOwner = viewModelStores[backStackEntryId]
        if (viewModelStoreOwner == null) {
            viewModelStoreOwner = NavStoreOwner()
            viewModelStores[backStackEntryId] = viewModelStoreOwner
        }
        return viewModelStoreOwner
    }

    override fun onCleared() {
        for (store in viewModelStores.values) {
            store.viewModelStore.clear()
        }
        viewModelStores.clear()
    }

    override fun getViewModelStore(backStackEntryId: String): ViewModelStore {
        return getStoreOwner(backStackEntryId).viewModelStore
    }

    override fun toString(): String {
        val sb = StringBuilder("NavViewModelStore{")
        sb.append(Integer.toHexString(System.identityHashCode(this)))
        sb.append("} ViewModelStores (")
        val viewModelStoreIterator: Iterator<String> = viewModelStores.keys.iterator()
        while (viewModelStoreIterator.hasNext()) {
            sb.append(viewModelStoreIterator.next())
            if (viewModelStoreIterator.hasNext()) {
                sb.append(", ")
            }
        }
        sb.append(')')
        return sb.toString()
    }
}