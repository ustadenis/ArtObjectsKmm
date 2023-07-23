package com.ssa.domain.repository

import com.ssa.domain.model.ArtObjectDetails
import com.ssa.domain.model.Images
import com.ssa.domain.model.MuseumCollection

interface IApiRepository {
    suspend fun getCollectionAsync(page: Int, itemsPerPage: Int = 10): MuseumCollection

    suspend fun getArtObjectImagesAsync(objectId: String): Images

    suspend fun getArtObjectDetailsAsync(objectId: String): ArtObjectDetails
}