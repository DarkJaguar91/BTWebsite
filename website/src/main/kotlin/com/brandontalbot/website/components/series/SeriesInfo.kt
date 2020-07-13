package com.brandontalbot.website.components.series

import com.brandontalbot.common.client.server.ServerClient
import com.brandontalbot.common.client.sonarr.model.SonarrSeries
import com.brandontalbot.common.client.sonarr.model.SonarrSeriesCoverType
import com.brandontalbot.website.components.common.Loading
import com.brandontalbot.website.components.common.img
import com.brandontalbot.website.components.common.spinner
import com.brandontalbot.website.config.WebsiteContext
import io.ktor.client.features.*
import io.ktor.http.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import react.*
import react.dom.h1
import react.dom.h2
import styled.css
import styled.styledDiv

fun RBuilder.seriesInfo(
    id: Long,
    client: ServerClient = WebsiteContext.serverClientConfig.client
) = child(seriesInfo) {
    attrs.seriesId = id
    attrs.client = client
}

private interface SeriesInfoProps : RProps {
    var seriesId: Long
    var client: ServerClient
}

private val seriesInfo = functionalComponent<SeriesInfoProps> { props ->
    val (loading, setLoading) = useState(true)
    val (series, setSeries) = useState<SonarrSeries?>(null)

    useEffect(listOf()) {
        GlobalScope.launch {
            try {
                setSeries(props.client.series(props.seriesId))
            } catch (e: ClientRequestException) {
                if (e.response.status != HttpStatusCode.NotFound) {
                    console.error("Unexpected status code: ${e.response.status}: ${e.message}")
                }
            } catch (e: Exception) {
                console.error(e.message)
            }
            setLoading(false)
        }
    }

    if (loading) {
        spinner(centered = true)
    } else {
        if (series == null) {
            h1 { +"no episode with ID ${props.seriesId} found." }
        } else {
            styledDiv {
                css {

                }
                img(
                    src = series.images.findLast { it.coverType == SonarrSeriesCoverType.banner }?.url,
                    classes = "banner",
                    loading = Loading.lazy
                )
                h2 { +series.title }
            }
        }
    }
}
