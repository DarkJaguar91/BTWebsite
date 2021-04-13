package series

import kotlinx.css.*
import kotlinx.css.properties.*
import styled.StyleSheet
import kotlin.time.seconds

object SeriesStyles : StyleSheet("SeriesStyles", isStatic = true) {
    val seriesList by css {
        display = Display.grid
        gap = Gap("8px")
        gridTemplateColumns = GridTemplateColumns("repeat(auto-fill, minmax(16em, 1fr))")
        gridTemplateRows = GridTemplateRows("1fr")
        margin(0.px, LinearDimension.auto, 80.px)
        maxWidth = 80.vw
    }

    val seriesItem by css {
        display = Display.flex
        width = 100.pct
        borderRadius = 25.px
        boxShadow(
            Color("#000000AA"),
            blurRadius = 8.px,
            offsetY = 8.px,
            offsetX = 2.px
        )
        transition(duration = Time("0.3s"))

        hover {
            boxShadow(
                Color("#000000AA"),
                blurRadius = 12.px,
                offsetY = 8.px,
                offsetX = 2.px,
                spreadRadius = 5.px
            )
        }

        before {
            content = QuotedString("")
            display = Display.block
            height = 0.px
            width = 0.px
            paddingBottom = LinearDimension("calc(250 / 170 * 100%)")
        }
    }

    val seriesImage by css {
        width = 100.pct
        height = 100.pct
        borderRadius = 25.px
    }
}
