package series

import com.brandontalbot.djcommon.client.server.ServerClient
import com.brandontalbot.djcommon.client.sonarr.model.SonarrSeries
import config.DJWebContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.css.*
import react.*
import react.dom.p
import styled.css
import styled.styledDiv
import styled.styledImg

external interface SeriesListProps : RProps {
    var serverClient: ServerClient
}

fun RBuilder.seriesList(
    serverClient: ServerClient = DJWebContext.serverConfig.client
) = child(seriesList) {
    attrs {
        this.serverClient = serverClient
    }
}

private val seriesList = functionalComponent<SeriesListProps> { props ->
    val (series, setSeries) = useState(listOf<SonarrSeries>())

    useEffect(emptyList()) {
        GlobalScope.launch {
            props.serverClient.series().sortedBy { it.sortTitle }.also {
                setSeries(it)
            }
        }
    }

    styledDiv {
        css {
            +SeriesStyles.seriesList
        }

        if (series.isEmpty()) {
            p { +"nada" }
        } else {
            series.forEach { item ->
                styledDiv {
                    css {
                        +SeriesStyles.seriesItem
                    }
                    styledImg(src = item.images.first { it.coverType.toLowerCase() == "poster" }.url) {
                        css {
                            +SeriesStyles.seriesImage
                        }
                        attrs {
                            this["loading"] = "lazy"
                        }
                    }
                }
            }
        }
    }
}
