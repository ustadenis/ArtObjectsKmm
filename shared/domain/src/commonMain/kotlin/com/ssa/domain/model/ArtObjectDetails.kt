package com.ssa.domain.model

data class ArtObjectDetails(
    val artObject: ArtObject?,
    val elapsedMilliseconds: Int? = 0
)

data class NormalizedColorsItem(
    val percentage: Int? = 0,
    val hex: String? = ""
)

data class PrincipalMakersItem(
    val placeOfBirth: String? = "",
    val occupation: List<String>?,
    val dateOfDeath: String? = "",
    val roles: List<String>?,
    val productionPlaces: List<String>?,
    val dateOfBirth: String? = "",
    val placeOfDeath: String? = "",
    val biography: String? = null,
    val dateOfDeathPrecision: String? = null,
    val qualification: String? = null,
    val nationality: String? = "",
    val unFixedName: String? = "",
    val name: String? = "",
    val dateOfBirthPrecision: String? = null
)

data class Label(
    val date: String? = "",
    val notes: String? = "",
    val description: String? = "",
    val title: String? = "",
    val makerLine: String? = ""
)

data class Acquisition(
    val date: String? = "",
    val method: String? = "",
    val creditLine: String? = ""
)

data class DimensionsItem(
    val unit: String? = "",
    val part: String? = "",
    val type: String? = "",
    val value: String? = ""
)

data class ColorsItem(
    val percentage: Int? = 0,
    val hex: String? = ""
)

data class Classification(val iconClassIdentifier: List<String>?)

data class SearchLinks(val search: String? = "")

data class ColorsWithNormalizationItem(
    val normalizedHex: String? = "",
    val originalHex: String? = ""
)

data class ArtObject(
    val scLabelLine: String? = "",
    val principalOrFirstMaker: String? = "",
    val labelText: String? = "",
    val principalMaker: String? = "",
    val objectNumber: String? = "",
    val normalizedColors: List<NormalizedColorsItem>?,
    val description: String? = "",
    val language: String? = "",
    val principalMakers: List<PrincipalMakersItem>?,
    val hasImage: Boolean? = false,
    val showImage: Boolean? = false,
    val title: String? = "",
    val colors: List<ColorsItem>?,
    val physicalMedium: String? = "",
    val webImage: WebImage?,
    val subTitle: String? = "",
    val copyrightHolder: String? = "",
    val artistRole: String? = "",
    val plaqueDescriptionEnglish: String? = "",
    val links: SearchLinks?,
    val priref: String? = "",
    val dating: Dating?,
    val id: String? = "",
    val acquisition: Acquisition?,
    val objectCollection: List<String>?,
    val colorsWithNormalization: List<ColorsWithNormalizationItem>?,
    val documentation: List<String>?,
    val productionPlaces: List<String>?,
    val titles: List<String>?,
    val label: Label?,
    val plaqueDescriptionDutch: String? = "",
    val classification: Classification?,
    val historicalPersons: List<String>?,
    val materials: List<String>?,
    val location: String? = "",
    val objectTypes: List<String>?,
    val dimensions: List<DimensionsItem>?,
    val longTitle: String? = ""
)

data class Dating(
    val period: Int? = 0,
    val sortingDate: Int? = 0,
    val yearLate: Int? = 0,
    val yearEarly: Int? = 0,
    val presentingDate: String? = ""
)
