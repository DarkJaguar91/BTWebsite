package com.brandontalbot.website.components.series

import com.brandontalbot.website.constant.Colors
import kotlinx.css.*
import kotlinx.css.properties.Time
import kotlinx.css.properties.boxShadow
import kotlinx.css.properties.cubicBezier
import kotlinx.css.properties.transition
import styled.StyleSheet

object SeriesListStyles : StyleSheet("SeriesStyles") {
    private const val borderRadius = 5

    val search by css {
        display = Display.flex
        width = 100.pct
        justifyContent = JustifyContent.center

        child("input") {
            +searchBox
        }
    }

    val container by css {
        display = Display.grid
        gridTemplateColumns = GridTemplateColumns.auto
        gap = Gap("0.5rem")
        margin(0.5.rem)

        media("screen and (min-width: 576px)") {
            gridTemplateColumns = GridTemplateColumns.auto
        }

        media("screen and (min-width: 768px)") {
            gridTemplateColumns = GridTemplateColumns.repeat("2, auto")
        }

        media("screen and (min-width: 992px)") {
            gridTemplateColumns = GridTemplateColumns.repeat("4, auto")
        }

        media("screen and (min-width: 1200px)") {
            gridTemplateColumns = GridTemplateColumns.repeat("6, auto")
        }

        child(".item") {
            +seriesItem
        }
    }

    private val searchBox by css {
        textAlign = TextAlign.center
        width = 80.pct
        height = 2.rem
        color = Color.white
        backgroundColor = Colors.background
        border = "2px solid ${Color.grey}"
    }

    private val seriesItem by css {
        textAlign = TextAlign.center
        color = Color.red
        borderRadius = SeriesListStyles.borderRadius.px
        cursor = Cursor.pointer

        boxShadow(rgba(0, 0, 0, 0.4), 4.px, 8.px, 8.px, 0.px)

        transition("all", Time("0.6s"), cubicBezier(0.165, 0.84, 0.44, 1.0))

        hover {
            boxShadow(rgba(0, 0, 0, 0.4), 8.px, 16.px, 8.px, 0.px)
        }

        child(".content") {
            +seriesContent
        }
    }

    private val seriesContent by css {
        position = Position.relative
        paddingTop = 147.pct

        child("img") {
            +seriesImage
        }
        child(".title") {
            +seriesTitle
        }
    }

    private val seriesImage by css {
        position = Position.absolute
        top = 0.px
        left = 0.px
        width = 100.pct
        height = 100.pct
        objectFit = ObjectFit.cover
        borderRadius = SeriesListStyles.borderRadius.px
    }

    private val seriesTitle by css {
        position = Position.absolute
        top = 0.px
        left = 0.px
        right = 0.px
        borderTopLeftRadius = SeriesListStyles.borderRadius.px
        borderTopRightRadius = SeriesListStyles.borderRadius.px
        padding(8.px)

        zIndex = 3

        color = Color.lightGrey
        backgroundColor = Color("#12121299")
        fontSize = (1.3).vw
        fontStyle = FontStyle.italic
        fontWeight = FontWeight("400")
    }
}
