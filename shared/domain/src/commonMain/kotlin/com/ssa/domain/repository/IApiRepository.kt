package com.ssa.domain.repository

import com.ssa.domain.model.ArtObjectDetails
import com.ssa.domain.model.Images
import com.ssa.domain.model.MuseumCollection

interface IApiRepository {
    suspend fun getCollection(page: Int, itemsPerPage: Int = 10): MuseumCollection

    suspend fun getArtObjectImages(objectId: String): Images

    suspend fun getArtObjectDetails(objectId: String): ArtObjectDetails
}