package Plex.model

import kotlinx.serialization.Serializable

@Serializable
data class PlexUser(
    val id: Int,
    val username: String,
    val uuid: String,
    val authToken: String,
    val email: String,
    val title: String,
    val friendlyName: String,
    val guest: Boolean,
    val joinedAt: Int,
    val `protected`: Boolean,
    val rememberExpiresAt: Int,
    val restricted: Boolean,
    val roles: List<String>,
    val services: List<Service>,
    val thumb: String,
    val twoFactorEnabled: Boolean,
)