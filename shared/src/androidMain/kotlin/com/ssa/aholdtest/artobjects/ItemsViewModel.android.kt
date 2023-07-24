package com.ssa.aholdtest.artobjects

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.ssa.domain.usecase.RetrieveArtObjectsByAuthorsOperation
import com.ssa.domain.usecase.RetrieveCollectionUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

actual class ItemsViewModel actual constructor(
    private val retrieveArtObjectsByAuthorsOperation: RetrieveArtObjectsByAuthorsOperation
) : BaseItemsViewModel() {

    private val loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val isEmpty: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val categories: MutableList<String> = mutableListOf()

    override val artObjects: Flow<PagingData<ArtObjectItemType>> = Pager(
        pagingSourceFactory = { ArtObjectsPagingSource() },
        config = PagingConfig(pageSize = 10, initialLoadSize = 20, enablePlaceholders = false)
    ).flow.cachedIn(viewModelScope.coroutineScope)

    override val screenState: StateFlow<ScreenState> =
        loading.combine(isEmpty) { loading, empty ->
            when {
                empty && loading -> ScreenState.Loading
                empty && !loading -> ScreenState.Empty
                else -> ScreenState.Data(loading)
            }
        }.stateIn(viewModelScope.coroutineScope, SharingStarted.Lazily, ScreenState.Empty)

    inner class ArtObjectsPagingSource : PagingSource<Int, ArtObjectItemType>() {
        override fun getRefreshKey(state: PagingState<Int, ArtObjectItemType>): Int? {
            return state.anchorPosition?.let { anchorPosition ->
                state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                    ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
            }
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtObjectItemType> {
            return try {
                val page = params.key ?: 0
                val size = params.loadSize
                val data = loadPage(page, size)
                isEmpty.emit(page == 0 && data.isEmpty())
                LoadResult.Page(
                    data = data,
                    prevKey = if (page == 0) null else page - 1,
                    nextKey = if (data.isEmpty()) null else page + 1
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }

        private suspend fun loadPage(startPosition: Int, count: Int): List<ArtObjectItemType> {
            loading.emit(true)
            val result = retrieveArtObjectsByAuthorsOperation.invoke(
                RetrieveCollectionUseCase.Params(
                    startPosition,
                    count
                )
            ).entries
            loading.emit(false)

            return result.map { entry ->
                entry.value.map { ArtObjectItemType.ArtObject(it) }
                    .toMutableList<ArtObjectItemType>()
                    .also {
                        if (!categories.contains(entry.key)) {
                            it.add(0, ArtObjectItemType.Category(entry.key))
                            categories.add(entry.key)
                        }
                    }
            }.flatten()
        }
    }
}