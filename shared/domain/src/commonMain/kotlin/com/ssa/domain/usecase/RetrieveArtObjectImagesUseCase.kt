package com.ssa.domain.usecase

import com.ssa.domain.exception.IllegalParamsException
import com.ssa.domain.model.LevelsItem
import com.ssa.domain.repository.IApiRepository
import com.ssa.domain.usecase.RetrieveArtObjectImagesUseCase.Params

class RetrieveArtObjectImagesUseCase(
    private val repository: IApiRepository
) : BaseUseCase<Params, List<LevelsItem>>() {

    override suspend fun run(params: Params?): List<LevelsItem> {
        params ?: throw IllegalParamsException
        return repository.getArtObjectImages(params.objectId).levels
            ?: emptyList()
    }

    data class Params(
        val objectId: String
    )
}