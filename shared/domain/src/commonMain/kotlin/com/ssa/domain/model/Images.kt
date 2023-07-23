package com.ssa.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LevelsItem(
    val tiles: List<TilesItem>?,
    val name: String,
    val width: Int,
    val height: Int
)

@Serializable
data class Images(val levels: List<LevelsItem>?)

@Serializable
data class TilesItem(
    val x: Int,
    val y: Int,
    val url: String
)


