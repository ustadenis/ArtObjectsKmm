package com.ssa.domain

import com.ssa.domain.exception.IllegalParamsException
import com.ssa.domain.exception.UnableRetrieveArtObjectDetailsException
import com.ssa.domain.model.ArtObject
import com.ssa.domain.model.ArtObjectDetails
import com.ssa.domain.repository.IApiRepository
import com.ssa.domain.usecase.RetrieveArtObjectDetailsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RetrieveArtObjectDetailsUseCaseTest {

    private lateinit var useCase: RetrieveArtObjectDetailsUseCase

    private val repository: IApiRepository = mockk()

    @Before
    fun before() {
        useCase = RetrieveArtObjectDetailsUseCase(repository)
    }

    @Test
    fun `Retrieve art objects details successfully`() = runBlocking {
        val objectId = "KJA_LSF_KJ"
        val params = RetrieveArtObjectDetailsUseCase.Params(objectId)

        val details = mockk<ArtObject>()
        val objectDetails = mockk<ArtObjectDetails>() {
            every { artObject } returns details
        }

        coEvery {
            repository.getArtObjectDetails(objectId)
        } returns objectDetails

        val result = useCase.invoke(params)

        coVerify {
            repository.getArtObjectDetails(objectId)
        }
        assert(result == details)
    }

    @Test(expected = IllegalParamsException::class)
    fun `Retrieve art objects details invalid params`() = runBlocking {
        useCase.invoke(null)
        Unit
    }

    @Test(expected = UnableRetrieveArtObjectDetailsException::class)
    fun `Retrieve art objects details failed`() = runBlocking {
        val objectId = "KJA_LSF_KJ"
        val params = RetrieveArtObjectDetailsUseCase.Params(objectId)

        val objectDetails = mockk<ArtObjectDetails>() {
            every { artObject } returns null
        }

        coEvery {
            repository.getArtObjectDetails(objectId)
        } returns objectDetails

        useCase.invoke(params)

        Unit
    }
}