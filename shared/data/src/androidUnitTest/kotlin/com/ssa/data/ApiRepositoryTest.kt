package com.ssa.data

import com.ssa.data.repository.ApiRepository
import com.ssa.domain.repository.IApiRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test

class ApiRepositoryTest {

    private lateinit var repository: IApiRepository

    private val httpClient: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    @Before
    fun before() {
        repository = ApiRepository(httpClient)
    }

    @Test
    fun `Test get collection api call success`() = runBlocking {
        val page = 0
        val itemsPerPage = 10

        val result = repository.getCollectionAsync(page, itemsPerPage)

        assert(result.artObjects?.size == itemsPerPage)
    }

    @Test(expected = NoTransformationFoundException::class)
    fun `Test get details api call failed`() = runBlocking {
        val objectId = "asd_Asd"

        repository.getArtObjectDetailsAsync(objectId)

        Unit
    }

    @Test
    fun `Test get details api call success`() = runBlocking {
        val objectId = "SK-C-5"

        val result = repository.getArtObjectDetailsAsync(objectId)

        assert(result.artObject != null)
    }

    @Test(expected = NoTransformationFoundException::class)
    fun `Test get images api call failed`() = runBlocking {
        val objectId = "asd_Asd"

        repository.getArtObjectImagesAsync(objectId)

        Unit
    }
}