package com.brandontalbot.common.client.server

import com.brandontalbot.common.client.sonarr.model.SonarrSeries
import com.brandontalbot.common.client.tvdb.model.TvDbSeries
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class ServerClient(
    private val client: HttpClient,
    private val host: String,
    private val port: Int,
    private val protocol: URLProtocol
) {
    suspend fun searchSeries(name: String) = get<List<TvDbSeries>>("series", "search", name)

    suspend fun series() = get<List<SonarrSeries>>("series")

    suspend fun series(id: Long) = get<SonarrSeries>("series", id.toString())

    private suspend inline fun <reified T> get(
        vararg path: String,
        params: List<Pair<String, Any>>? = null
    ): T = client.get<T> {
        url {
            host = this@ServerClient.host
            port = this@ServerClient.port
            protocol = this@ServerClient.protocol
            path(*path)
            params?.forEach {
                parameters[it.first] = it.second.toString()
            }
        }
    }
}
