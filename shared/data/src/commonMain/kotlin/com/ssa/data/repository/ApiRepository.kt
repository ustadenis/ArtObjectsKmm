package com.ssa.data.repository

import com.ssa.data.API_KEY
import com.ssa.data.BASE_URL
import com.ssa.domain.model.ArtObjectDetails
import com.ssa.domain.model.Images
import com.ssa.domain.model.MuseumCollection
import com.ssa.domain.repository.IApiRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiRepository(private val httpClient: HttpClient) : IApiRepository {

    override suspend fun getCollection(page: Int, itemsPerPage: Int): MuseumCollection {
        return httpClient.get(
            "$BASE_URL/collection?key=$API_KEY&involvedMaker=Rembrandt+van+Rijn&p=$page&ps=$itemsPerPage&s=artist"
        ).body()
    }

    override suspend fun getArtObjectImages(objectId: String): Images {
        return httpClient.get(
            "$BASE_URL/collection/$objectId/tiles?key=$API_KEY"
        ).body()
    }

    override suspend fun getArtObjectDetails(objectId: String): ArtObjectDetails {
        return httpClient.get(
            "$BASE_URL/collection/$objectId?key=$API_KEY"
        ).body()
    }
}