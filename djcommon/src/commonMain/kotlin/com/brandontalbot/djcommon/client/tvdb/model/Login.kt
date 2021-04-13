package com.brandontalbot.djcommon.client.tvdb.model

import kotlinx.serialization.Serializable

@Serializable
internal data class Login(
    val apikey: String,
    val username: String,
    val userkey: String
)

@Serializable
internal data class LoginResponse(
    val token: String
)
