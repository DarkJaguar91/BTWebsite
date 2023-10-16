package Plex.model

import kotlinx.serialization.Serializable

@Serializable
data class PlexPinStatus(
    val authToken: String? = null,
)
