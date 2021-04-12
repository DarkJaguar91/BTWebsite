package com.brandontalbot.djcommon.client.sonarr

import com.brandontalbot.djcommon.client.sonarr.model.SonarrSeries
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class SonarrClient(
    private val client: HttpClient,
    private val token: String,
    private val host: String,
    private val port: Int = 443,
    private val protocol: URLProtocol = URLProtocol.HTTPS,
) {
    suspend fun series() = get<List<SonarrSeries>>("series")

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
        params: List<Pair<String, Any>>? = null,
    ): T = client.get {
        url {
            this.host = this@SonarrClient.host
            this.port = this@SonarrClient.port
            this.protocol = this@SonarrClient.protocol
            this.path("api", *path)
            params?.forEach {
                parameters.append(it.first, it.second.toString())
            }
        }
        header("X-Api-Key", token)
    }
}
