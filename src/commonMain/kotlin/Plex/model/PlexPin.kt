package Plex.model

import kotlinx.serialization.Serializable

@Serializable
data class PlexPin(
    val id: Long,
    val code: String,
)
