package com.brandontalbot.website.components.series

import com.brandontalbot.common.client.server.ServerClient
import com.brandontalbot.common.client.tvdb.model.TvDbSeries
import com.brandontalbot.website.components.common.*
import com.brandontalbot.website.components.common.svg.*
import com.brandontalbot.website.config.WebsiteContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.css.Color
import kotlinx.css.color
import kotlinx.html.InputType
import kotlinx.html.js.onInputFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.*
import styled.css
import styled.styledDiv
import styled.styledP

fun RBuilder.seriesSearch(
    client: ServerClient = WebsiteContext.serverClientConfig.client
) = child(seriesSearch) {
    attrs.client = client
}

private interface SeriesSearchProps : RProps {
    var client: ServerClient
}

private val seriesSearch = functionalComponent<SeriesSearchProps> { props ->
    val (loading, setLoading) = useState(false)
    val (series, setSeries) = useState<List<TvDbSeries>>(listOf())

    styledDiv {
        css {
            +SeriesSearchStyles.search
        }

        input(type = InputType.text, name = "search") {
            attrs["placeholder"] = "Series Name"
            attrs.onInputFunction = {
                val target = it.target as HTMLInputElement
                val searchText = target.value
                search(searchText, setLoading, setSeries, props.client)
            }
        }
    }

    if (loading) {
        spinner(centered = true)
    } else {
        styledDiv {
            css {
                +SeriesSearchStyles.seriesList
            }

            series.forEach(::seriesItem)
        }
    }
}

private const val missingImage = "https://artworks.thetvdb.com/banners/images/missing/series.jpg"
private fun RBuilder.seriesItem(series: TvDbSeries) {
    div("item") {
        img(src = series.poster ?: missingImage, classes = "image") { }
        h3("title") { +series.seriesName }
        p("overview") { +(series.overview ?: "No details...") }
        styledP {
            css {
                classes.add("status")
                color = when (series.status.toLowerCase()) {
                    "continuing" -> Color.green
                    "ended" -> Color.red
                    else -> Color.yellow
                }
            }
            +series.status
        }
    }
}

private fun search(
    searchText: String,
    setLoading: RSetState<Boolean>,
    setSeries: RSetState<List<TvDbSeries>>,
    client: ServerClient
) {
    job?.cancel()
    job = GlobalScope.launch {
        delay(600)
        setLoading(true)
        try {
            if (searchText.isEmpty()) {
                setSeries(listOf())
            } else {
                setSeries(client.searchSeries(searchText))
            }
        } finally {
            setLoading(false)
        }
    }
}
