package com.brandontalbot.djcommon.client.server

import com.brandontalbot.djcommon.client.sonarr.model.SonarrSeries
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class ServerClient(
    private val client: HttpClient,
    private val host: String? = null,
    private val port: Int? = null,
    private val protocol: URLProtocol? = null,
) {
    suspend fun series(): List<SonarrSeries> = get("sonarr/series")

    private suspend inline fun <reified T> get(
        vararg path: String,
        params: List<Pair<String, Any>>? = null,
    ): T = client.get {
        url {
            this@ServerClient.host?.let { this.host = it }
            this@ServerClient.port?.let { this.port = it }
            this@ServerClient.protocol?.let { this.protocol = it }
            this.path(*path)
            params?.forEach {
                parameters.append(it.first, it.second.toString())
            }
        }
    }
}
