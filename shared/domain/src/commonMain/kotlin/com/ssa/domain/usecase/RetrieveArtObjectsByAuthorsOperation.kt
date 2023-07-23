package com.ssa.domain.usecase

import com.ssa.domain.exception.IllegalParamsException
import com.ssa.domain.model.ArtObjectsItem

class RetrieveArtObjectsByAuthorsOperation(
    private val retrieveCollectionUseCase: RetrieveCollectionUseCase
) : BaseUseCase<RetrieveCollectionUseCase.Params, Map<String, List<ArtObjectsItem>>>() {

    override suspend fun run(params: RetrieveCollectionUseCase.Params?): Map<String, List<ArtObjectsItem>> {
        params ?: throw IllegalParamsException

        val artObjects = retrieveCollectionUseCase.invoke(params).artObjects
            ?: emptyList()

        return artObjects.groupBy {
            it.principalOrFirstMaker
        }
    }

}