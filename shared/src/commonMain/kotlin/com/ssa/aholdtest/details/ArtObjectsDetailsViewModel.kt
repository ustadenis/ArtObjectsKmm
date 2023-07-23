package com.ssa.aholdtest.details

import com.ssa.aholdtest.base.BaseViewModel
import com.ssa.aholdtest.details.IArtObjectDetailsViewModel.Event
import com.ssa.aholdtest.details.IArtObjectDetailsViewModel.ScreenState
import com.ssa.domain.usecase.RetrieveArtObjectDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow

class ArtObjectsDetailsViewModel(
    private val retrieveArtObjectDetailsUseCase: RetrieveArtObjectDetailsUseCase
) : BaseViewModel<Event>(), IArtObjectDetailsViewModel {

    override val screenState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)

    override suspend fun onEvent(event: Event) {
        when (event) {
            is Event.RetrieveDetails -> handleRetrieveDetails(event)
        }
    }

    override fun onError(exception: Throwable, event: Event) {
        super.onError(exception, event)
        when (event) {
            is Event.RetrieveDetails -> screenState.tryEmit(ScreenState.Error)
        }
    }

    private suspend fun handleRetrieveDetails(event: Event.RetrieveDetails) {
        screenState.emit(ScreenState.Loading)
        val result = retrieveArtObjectDetailsUseCase.invoke(
            RetrieveArtObjectDetailsUseCase.Params(event.objectId)
        )
        screenState.emit(ScreenState.Data(result))
    }

}