package com.ssa.domain.usecase

import com.ssa.domain.exception.IllegalParamsException
import com.ssa.domain.model.MuseumCollection
import com.ssa.domain.repository.IApiRepository

class RetrieveCollectionUseCase(
    private val repository: IApiRepository
) : BaseUseCase<RetrieveCollectionUseCase.Params, MuseumCollection>() {

    override suspend fun run(params: Params?): MuseumCollection {
        params ?: throw IllegalParamsException
        return repository.getCollection(params.page, params.itemsPerPage)
    }

    data class Params(
        val page: Int,
        val itemsPerPage: Int = 10
    )
}