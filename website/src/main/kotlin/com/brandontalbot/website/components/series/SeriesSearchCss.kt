package com.brandontalbot.website.components.series

import com.brandontalbot.website.constant.Colors
import kotlinx.css.*
import kotlinx.css.properties.boxShadow
import styled.StyleSheet

object SeriesSearchStyles : StyleSheet("SeriesSearcg") {
    val search by css {
        display = Display.flex
        width = 100.pct
        justifyContent = JustifyContent.center

        child("input") {
            +searchBox
        }
    }

    val seriesList by css {
        display = Display.grid
        padding(0.8.rem)

        gridTemplateColumns = GridTemplateColumns.repeat("1, auto")
        gap = Gap("0.7rem")

        child(".item") {
            +seriesItem
        }
    }

    private val seriesItem by css {
        display = Display.grid
        gridTemplateColumns = GridTemplateColumns.repeat("12, 1fr")
        gridTemplateRows = GridTemplateRows.repeat("7, 1fr")

        padding(0.8.rem)
        backgroundColor = Color("#a9a9a9")
        borderRadius = 8.px
        boxShadow(rgba(0, 0, 0, 0.4), 4.px, 8.px, 8.px, 0.px)

        child(".image") {
            +seriesImage
        }

        child(".title") {
            +seriesTitle
        }

        child(".overview") {
            +seriesOverview
        }

        child(".status") {
            +seriesStatus
        }
    }

    private val seriesImage by css {
        gridRow = GridRow("1 / 8")
        gridColumn = GridColumn("1 / 3")

        maxWidth = 100.pct
        objectFit = ObjectFit.cover
        borderRadius = 8.px
    }

    private val seriesTitle by css {
        gridRow = GridRow("1 / 3")
        gridColumn = GridColumn("3 / 13")

        margin(0.5.rem)
    }

    private val seriesOverview by css {
        gridRow = GridRow("3 / 8")
        gridColumn = GridColumn("3 / 13")

        margin(0.5.rem)
    }

    private val seriesStatus by css {
        gridRowEnd = GridRowEnd("8")
        gridColumnEnd = GridColumnEnd("13")

        margin(0.5.rem)
    }

    private val searchBox by css {
        textAlign = TextAlign.center
        width = 80.pct
        height = 2.rem
        color = Color.white
        backgroundColor = Colors.background
        border = "2px solid ${Color.grey}"
    }
}
