package com.ssa.aholdtest.di

import com.ssa.data.repository.ApiRepository
import com.ssa.domain.repository.IApiRepository
import com.ssa.domain.usecase.RetrieveArtObjectDetailsUseCase
import com.ssa.domain.usecase.RetrieveArtObjectImagesUseCase
import com.ssa.domain.usecase.RetrieveArtObjectsByAuthorsOperation
import com.ssa.domain.usecase.RetrieveCollectionUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                })
            }
        }
    }
}

val repoModule = module {
    factory { ApiRepository(get()) as IApiRepository }
}

val useCaseModule = module {
    factory { RetrieveCollectionUseCase(get()) }
    factory { RetrieveArtObjectImagesUseCase(get()) }
    factory { RetrieveArtObjectsByAuthorsOperation(get()) }
    factory { RetrieveArtObjectDetailsUseCase(get()) }
}