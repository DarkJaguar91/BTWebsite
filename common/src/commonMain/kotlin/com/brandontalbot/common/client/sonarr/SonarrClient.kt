package com.brandontalbot.common.client.sonarr

import com.brandontalbot.common.client.sonarr.model.SonarrSeries
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class SonarrClient(
    private val httpClient: HttpClient,
    private val host: String,
    private val port: Int,
    private val protocol: URLProtocol,
    private val token: String
) {
    suspend fun series(): List<SonarrSeries> = get("series")

    suspend fun series(id: Long): SonarrSeries = get("series", id.toString())

    suspend fun getImage(
        id: Long,
        type: String,
        lastWrite: String? = null
    ): HttpResponse = get(
        "MediaCover", id.toString(), type,
        params = listOfNotNull(lastWrite?.let { "lastWrite" to it })
    )

    private suspend inline fun <reified T> get(
        vararg path: String,
        params: List<Pair<String, Any>>? = null
    ): T = httpClient.get<T> {
        url {
            this.host = this@SonarrClient.host
            this.port = this@SonarrClient.port
            this.protocol = this@SonarrClient.protocol
            this.path("api", *path)
            params?.forEach {
                parameters.append(it.first, it.second.toString())
            }
            header("X-Api-Key", token)
        }
    }
}
