package com.ssa.domain

import com.ssa.domain.exception.IllegalParamsException
import com.ssa.domain.model.Images
import com.ssa.domain.model.LevelsItem
import com.ssa.domain.repository.IApiRepository
import com.ssa.domain.usecase.RetrieveArtObjectImagesUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RetrieveArtObjectImagesUseCaseTest {

    private lateinit var useCase: RetrieveArtObjectImagesUseCase

    private val repository: IApiRepository = mockk()

    @Before
    fun before() {
        useCase = RetrieveArtObjectImagesUseCase(repository)
    }

    @Test
    fun `Retrieve images successfully`() = runBlocking {
        val objectId = "KJA_LSF_KJ"
        val params = RetrieveArtObjectImagesUseCase.Params(objectId)

        val levels = listOf(mockk<LevelsItem>())
        val images = mockk<Images>() {
            every { this@mockk.levels } returns levels
        }

        coEvery {
            repository.getArtObjectImagesAsync(objectId)
        } returns images

        val result = useCase.invoke(params)

        coVerify {
            repository.getArtObjectImagesAsync(objectId)
        }
        assert(result == levels)
    }

    @Test(expected = IllegalParamsException::class)
    fun `Retrieve images invalid params`() = runBlocking {
        useCase.invoke(null)
        Unit
    }
}