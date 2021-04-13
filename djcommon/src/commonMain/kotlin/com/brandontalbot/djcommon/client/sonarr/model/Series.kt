package com.brandontalbot.djcommon.client.sonarr.model

import kotlinx.serialization.Serializable

@Serializable
data class SonarrSeriesTitle(
    val title: String,
    val seasonNumber: Int,
)

@Serializable
data class SonarrSeriesImage(
    val coverType: String,
    val url: String,
)

@Serializable
data class SonarrSeriesSeason(
    val seasonNumber: Long,
    val monitored: Boolean,
)

@Serializable
data class SonarrSeries(
    val id: Long,
    val title: String,
    val cleanTitle: String,
    val titleSlug: String,
    val sortTitle: String,
    val alternateTitles: List<SonarrSeriesTitle>,
    val seasonCount: Long,
    val totalEpisodeCount: Long,
    val episodeCount: Long,
    val episodeFileCount: Long,
    val sizeOnDisk: Long,
    val status: String,
    val overview: String,
    val network: String,
    val images: List<SonarrSeriesImage>,
    val seasons: List<SonarrSeriesSeason>,
    val year: Long,
    val path: String,
    val profileId: Long,
    val seasonFolder: Boolean,
    val monitored: Boolean,
    val useSceneNumbering: Boolean,
    val runtime: Long,
    val tvdbId: Long,
    val tvRageId: Long,
    val tvMazeId: Long,
    val firstAired: String,
    val lastInfoSync: String,
    val seriesType: String,
    val imdbId: String,
    val genres: List<String>,
    val added: String,
    val qualityProfileId: Long,
    val previousAiring: String? = null,
    val airTime: String? = null,
    val certification: String? = null,
)
