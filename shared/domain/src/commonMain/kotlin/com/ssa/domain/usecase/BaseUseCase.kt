package com.ssa.domain.usecase

abstract class BaseUseCase<TParams, TResult> {
    suspend operator fun invoke(params: TParams? = null): TResult {
        try {
            return run(params)
        } catch (ex: Exception) {
            onError(ex)
        }
    }

    protected abstract suspend fun run(params: TParams?): TResult

    protected open fun onError(ex: Exception): Nothing {
        throw ex
    }
}