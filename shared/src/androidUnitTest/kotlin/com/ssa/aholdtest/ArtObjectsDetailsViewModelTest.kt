package com.ssa.aholdtest

import com.ssa.aholdtest.details.ArtObjectsDetailsViewModel
import com.ssa.aholdtest.details.IArtObjectDetailsViewModel
import com.ssa.aholdtest.details.IArtObjectDetailsViewModel.Event
import com.ssa.aholdtest.details.IArtObjectDetailsViewModel.ScreenState
import com.ssa.domain.exception.UnableRetrieveArtObjectDetailsException
import com.ssa.domain.model.ArtObject
import com.ssa.domain.usecase.RetrieveArtObjectDetailsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ObsoleteCoroutinesApi::class, ExperimentalCoroutinesApi::class)
class ArtObjectsDetailsViewModelTest : BaseLifecycleVmTest() {

    private val retrieveUseCase = mockk<RetrieveArtObjectDetailsUseCase>(relaxed = true)

    private val viewModel: IArtObjectDetailsViewModel by lazy {
        ArtObjectsDetailsViewModel(retrieveUseCase)
    }

    private lateinit var screenStateObserver: suspend (ScreenState) -> Unit

    @Before
    override fun before() {
        super.before()
        screenStateObserver = mockk(relaxed = true)

        viewModel.screenState.onEach(screenStateObserver).launchIn(testViewModelScope)
    }

    @Test
    fun `verify retrieve details success`() {
        val objectId = "ASD_ASFA"
        val event = Event.RetrieveDetails(objectId)
        val params = RetrieveArtObjectDetailsUseCase.Params(objectId)
        val details = mockk<ArtObject>()
        coEvery {
            retrieveUseCase.invoke(params)
        } returns details

        viewModel.sendEvent(event)

        val stateSlot = slot<ScreenState>()
        coVerify(timeout = timeout, exactly = 2) {
            screenStateObserver.invoke(capture(stateSlot))
        }

        assert(stateSlot.captured is ScreenState.Data)
        assertEquals((stateSlot.captured as ScreenState.Data).artObject, details)
    }

    @Test
    fun `verify retrieve details failed`() {
        val objectId = "ASD_ASFA"
        val event = Event.RetrieveDetails(objectId)
        val params = RetrieveArtObjectDetailsUseCase.Params(objectId)
        val details = mockk<ArtObject>()
        coEvery {
            retrieveUseCase.invoke(params)
        } throws UnableRetrieveArtObjectDetailsException

        viewModel.sendEvent(event)

        val stateSlot = slot<ScreenState>()
        coVerify(timeout = timeout, exactly = 2) {
            screenStateObserver.invoke(capture(stateSlot))
        }

        assert(stateSlot.captured is ScreenState.Error)
    }

}