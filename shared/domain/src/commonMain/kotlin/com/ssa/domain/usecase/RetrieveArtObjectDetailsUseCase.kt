package com.ssa.domain.usecase

import com.ssa.domain.exception.IllegalParamsException
import com.ssa.domain.exception.UnableRetrieveArtObjectDetailsException
import com.ssa.domain.model.ArtObject
import com.ssa.domain.repository.IApiRepository
import com.ssa.domain.usecase.RetrieveArtObjectDetailsUseCase.Params

class RetrieveArtObjectDetailsUseCase(
    private val repository: IApiRepository
) : BaseUseCase<Params, ArtObject>() {

    override suspend fun run(params: Params?): ArtObject {
        params ?: throw IllegalParamsException
        return repository.getArtObjectDetailsAsync(params.objectId).artObject!!
    }

    override fun onError(ex: Exception): Nothing {
        when (ex) {
            is NullPointerException -> throw UnableRetrieveArtObjectDetailsException
            else -> throw ex
        }
    }

    data class Params(
        val objectId: String
    )
}