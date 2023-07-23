package com.ssa.domain.extension

fun <T> tryOrNull(block: () -> T): T? = try {
    block()
} catch (e: Throwable) {
    null
}

suspend fun <T> tryOrNullAsync(block: suspend () -> T): T? = try {
    block()
} catch (e: Throwable) {
    null
}