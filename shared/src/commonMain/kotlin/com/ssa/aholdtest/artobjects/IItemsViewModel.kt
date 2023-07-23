package com.ssa.aholdtest.artobjects

import com.ssa.aholdtest.base.IBaseViewModel
import com.ssa.domain.model.ArtObjectsItem
import kotlinx.coroutines.flow.StateFlow

expect interface IItemsViewModel : IBaseItemsViewModel

interface IBaseItemsViewModel : IBaseViewModel<Event> {
    val screenState: StateFlow<ScreenState>
}

sealed interface Event {
    data class RetrieveArtObjects(val page: Int) : Event
}

sealed interface ScreenState {
    object Loading : ScreenState
    data class Data(val loading: Boolean) : ScreenState
    object Empty : ScreenState
}

sealed interface ArtObjectItemType {
    data class Category(val name: String) : ArtObjectItemType
    data class ArtObject(val item: ArtObjectsItem) : ArtObjectItemType
}
