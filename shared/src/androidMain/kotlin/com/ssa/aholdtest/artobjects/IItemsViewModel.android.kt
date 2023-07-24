package com.ssa.aholdtest.artobjects

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

actual interface IItemsViewModel : IBaseItemsViewModel {
    val artObjects: Flow<PagingData<ArtObjectItemType>>
}
