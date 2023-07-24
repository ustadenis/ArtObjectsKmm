package com.ssa.aholdtest.artobjects

import kotlinx.coroutines.flow.Flow

actual interface IItemsViewModel : IBaseItemsViewModel {
    val artObjects: Flow<List<ArtObjectItemType>>
}
