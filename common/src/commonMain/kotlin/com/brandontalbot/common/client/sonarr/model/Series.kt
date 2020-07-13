package com.brandontalbot.common.client.sonarr.model

import kotlinx.serialization.Serializable

@Serializable
data class SonarrSeries(
    val id: Long,
    val title: String,
    val sortTitle: String,
    val overview: String,
    val images: List<SonarrSeriesImage>,
    val status: SeriesStatus
)

@Serializable
enum class SeriesStatus {
    continuing,
    ended,
    upcoming
}

@Serializable
data class SonarrSeriesImage(
    val coverType: SonarrSeriesCoverType,
    val url: String
)

@Serializable
enum class SonarrSeriesCoverType {
    fanart,
    banner,
    poster
}
