package com.ssa.aholdtest.artobjects

import com.ssa.aholdtest.base.BaseViewModel
import com.ssa.domain.usecase.RetrieveArtObjectsByAuthorsOperation

abstract class BaseItemsViewModel : BaseViewModel<Event>(), IItemsViewModel {

    override suspend fun onEvent(event: Event) {
        // no-op
    }

}

expect class ItemsViewModel(
    retrieveArtObjectsByAuthorsOperation: RetrieveArtObjectsByAuthorsOperation
) : BaseItemsViewModel