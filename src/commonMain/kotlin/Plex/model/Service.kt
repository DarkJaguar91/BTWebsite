package Plex.model

import kotlinx.serialization.Serializable

@Serializable
data class Service(
    val endpoint: String,
    val identifier: String,
    val secret: String? = null,
    val status: String,
    val token: String? = null,
)