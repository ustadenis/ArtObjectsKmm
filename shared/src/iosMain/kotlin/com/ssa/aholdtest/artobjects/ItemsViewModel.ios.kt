package com.ssa.aholdtest.artobjects

import com.rickclephas.kmm.viewmodel.coroutineScope
import com.ssa.domain.usecase.RetrieveArtObjectsByAuthorsOperation
import com.ssa.domain.usecase.RetrieveCollectionUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

internal const val ITEMS_PER_PAGE = 10

actual class ItemsViewModel actual constructor(
    private val retrieveArtObjectsByAuthorsOperation: RetrieveArtObjectsByAuthorsOperation
) : BaseItemsViewModel() {

    private val loading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    override val artObjects: MutableSharedFlow<List<ArtObjectItemType>> = MutableSharedFlow(
        replay = 1, extraBufferCapacity = 1
    )

    override val screenState: StateFlow<ScreenState> =
        loading.combine(artObjects) { loading, objects ->
            when {
                objects.isEmpty() && loading -> ScreenState.Loading
                objects.isEmpty() && !loading -> ScreenState.Empty
                else -> ScreenState.Data(loading)
            }
        }.stateIn(viewModelScope.coroutineScope, SharingStarted.Lazily, ScreenState.Empty)

    init {
        sendEvent(Event.RetrieveArtObjects(0))
    }

    override suspend fun onEvent(event: Event) {
        when (event) {
            is Event.RetrieveArtObjects -> handleRetrieveArtObjects(event)
        }
    }

    private suspend fun handleRetrieveArtObjects(event: Event.RetrieveArtObjects) {
        val result = retrieveArtObjectsByAuthorsOperation.invoke(
            RetrieveCollectionUseCase.Params(
                event.page,
                ITEMS_PER_PAGE
            )
        ).entries

        val currentItems = artObjects.replayCache.firstOrNull() ?: emptyList()

        val items = result.map { entry ->
            entry.value.map { ArtObjectItemType.ArtObject(it) }
                .toMutableList<ArtObjectItemType>()
                .also {
                    val category = ArtObjectItemType.Category(entry.key)
                    if (!currentItems.contains(category)) {
                        it.add(0, category)
                    }
                }
        }.flatten()

        val newArtObjects = currentItems.toMutableList().apply { addAll(items) }
        artObjects.emit(newArtObjects)
    }

}