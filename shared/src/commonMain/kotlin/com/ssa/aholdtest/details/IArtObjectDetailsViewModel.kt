package com.ssa.aholdtest.details

import com.ssa.aholdtest.base.IBaseViewModel
import com.ssa.aholdtest.details.IArtObjectDetailsViewModel.Event
import com.ssa.domain.model.ArtObject
import kotlinx.coroutines.flow.StateFlow

interface IArtObjectDetailsViewModel : IBaseViewModel<Event> {
    val screenState: StateFlow<ScreenState>

    sealed interface Event {
        data class RetrieveDetails(val objectId: String) : Event
    }

    sealed interface ScreenState {
        object Loading : ScreenState
        data class Data(val artObject: ArtObject) : ScreenState
        object Error : ScreenState
    }
}