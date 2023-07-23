package com.ssa.domain.model

data class LevelsItem(
    val tiles: List<TilesItem>?,
    val name: String,
    val width: Int,
    val height: Int
)

data class Images(val levels: List<LevelsItem>?)

data class TilesItem(
    val x: Int,
    val y: Int,
    val url: String
)


