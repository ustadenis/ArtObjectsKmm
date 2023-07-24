package com.ssa.domain

import com.ssa.domain.exception.IllegalParamsException
import com.ssa.domain.model.MuseumCollection
import com.ssa.domain.repository.IApiRepository
import com.ssa.domain.usecase.RetrieveCollectionUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RetrieveCollectionUseCaseTest {

    private lateinit var useCase: RetrieveCollectionUseCase

    private val repository: IApiRepository = mockk()

    @Before
    fun before() {
        useCase = RetrieveCollectionUseCase(repository)
    }

    @Test
    fun `Retrieve collections successfully`() = runBlocking {
        val page = 0
        val itemsPerPage = 10
        val params = RetrieveCollectionUseCase.Params(page, itemsPerPage)

        val collection = mockk<MuseumCollection>()

        coEvery {
            repository.getCollection(page, itemsPerPage)
        } returns collection

        val result = useCase.invoke(params)

        coVerify {
            repository.getCollection(page, itemsPerPage)
        }
        assert(result == collection)
    }

    @Test(expected = IllegalParamsException::class)
    fun `Retrieve collection invalid params`() = runBlocking {
        useCase.invoke(null)
        Unit
    }
}