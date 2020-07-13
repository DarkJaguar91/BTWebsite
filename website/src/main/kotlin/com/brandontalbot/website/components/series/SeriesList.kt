package com.brandontalbot.website.components.series

import com.brandontalbot.common.client.server.ServerClient
import com.brandontalbot.common.client.sonarr.model.SeriesStatus
import com.brandontalbot.common.client.sonarr.model.SonarrSeries
import com.brandontalbot.common.client.sonarr.model.SonarrSeriesCoverType
import com.brandontalbot.website.components.common.Loading
import com.brandontalbot.website.components.common.img
import com.brandontalbot.website.components.common.spinner
import com.brandontalbot.website.components.common.svg.*
import com.brandontalbot.website.config.WebsiteContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.html.InputType
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onInputFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.div
import react.dom.input
import styled.css
import styled.styledDiv
import styled.styledSvg
import kotlin.browser.window

fun RBuilder.seriesList(
    client: ServerClient = WebsiteContext.serverClientConfig.client
) = child(seriesList) {
    attrs.client = client
}

private interface SeriesListProps : RProps {
    var client: ServerClient
}

var job: Job? = null
private val seriesList = functionalComponent<SeriesListProps> { props ->
    val (series, setSeries) = useState(listOf<SonarrSeries>())
    val (loaded, setLoaded) = useState(false)
    val (search, setSearch) = useState("")

    useEffect(listOf()) {
        GlobalScope.launch {
            val response = props.client.series()

            setSeries(response)
            setLoaded(true)
        }
    }

    if (loaded) {
        styledDiv {
            css {
                +SeriesListStyles.search
            }

            input(type = InputType.text, name = "search") {
                attrs["placeholder"] = "Search..."
                attrs.onInputFunction = {
                    val target = it.target as HTMLInputElement
                    job?.cancel()
                    job = GlobalScope.launch {
                        delay(600)
                        setSearch(target.value)
                    }
                }
            }
        }

        styledDiv {
            css {
                +SeriesListStyles.container
            }

            series
                .filter {
                    if (search.isBlank()) {
                        true
                    } else {
                        it.title.contains(search, true)
                    }
                }
                .sortedBy { it.sortTitle }
                .forEach(::seriesCard)
        }
    } else {
        spinner(true)
    }
}

private fun RBuilder.seriesCard(series: SonarrSeries) {
    val image = series.images.findLast { it.coverType == SonarrSeriesCoverType.poster }?.url
    div("item") {
        key = series.id.toString()
        div("content") {
            attrs.onClickFunction = {
                window.location.hash = "#/series/${series.id}"
            }

            img(src = image, loading = Loading.lazy)
            div("title") { +series.title }
            banner(series)
        }
    }
}

private fun RBuilder.banner(series: SonarrSeries) {
    styledSvg {
        css {
            position = Position.absolute
            top = 0.px
            right = 0.px
            width = 30.pct
        }

        viewBox(0, 0, 200, 200)

        path {
            d {
                M(0, 0)
                L(70, 0)
                L(200, 130)
                L(200, 200)
                Z()
            }

            fill = when (series.status) {
                SeriesStatus.continuing -> Color.darkGreen
                SeriesStatus.ended -> Color.darkRed
                else -> Color.yellow
            }
        }

        g {
            transform {
                rotate(45)
                translate(90, -20)
            }

            text {
                x = 0
                y = 0
                fill = Color.white
                textLength = 100
                fontSize = 20

                +series.status.name
            }
        }
    }
}
