package com.brandontalbot.common.client.tvdb.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val data: List<TvDbSeries>
)

@Serializable
data class TvDbSeries(
    val id: Long,
    val seriesName: String,
    val slug: String,
    val status: String,
    val firstAired: String? = null,
    val overview: String? = null,
    val network: String? = null,
    val image: String? = null,
    val banner: String? = null,
    val poster: String? = null
)
