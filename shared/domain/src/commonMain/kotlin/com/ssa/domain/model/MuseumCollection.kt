package com.ssa.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MuseumCollection(
    val artObjects: List<ArtObjectsItem>?,
    val count: Int,
    val elapsedMilliseconds: Int
)

@Serializable
data class ArtObjectsItem(
    val principalOrFirstMaker: String,
    val webImage: WebImage?,
    val headerImage: HeaderImage?,
    val objectNumber: String,
    val productionPlaces: List<String>?,
    val links: Links?,
    val hasImage: Boolean?,
    val showImage: Boolean?,
    val id: String?,
    val title: String,
    val longTitle: String,
    val permitDownload: Boolean?
)

@Serializable
data class WebImage(
    val offsetPercentageY: Int,
    val offsetPercentageX: Int,
    val width: Int,
    val guid: String?,
    val url: String,
    val height: Int
)

@Serializable
data class Links(
    val web: String,
    val self: String
)

@Serializable
data class HeaderImage(
    val offsetPercentageY: Int?,
    val offsetPercentageX: Int?,
    val width: Int?,
    val guid: String?,
    val url: String?,
    val height: Int?
)


