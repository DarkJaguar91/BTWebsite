package com.brandontalbot.website.components

import com.brandontalbot.common.getEnv
import com.brandontalbot.website.components.navbar.navbar
import com.brandontalbot.website.components.series.seriesInfo
import com.brandontalbot.website.components.series.seriesList
import com.brandontalbot.website.components.series.seriesSearch
import org.w3c.dom.HashChangeEvent
import react.*
import react.router.dom.hashRouter
import react.router.dom.route
import react.router.dom.switch
import kotlin.browser.window

fun RBuilder.root() = child(root) { }

private val root = functionalComponent<RProps> {
    RootStyles.injectGlobalCss()

    console.log("Debug: ${getEnv("DEBUG")}")

    hashRouter {
        navbar()

        switch {
            route("/", exact = true) { seriesList() }
            route<IdProps>("/series/:id", exact = true) { props -> seriesInfo(props.match.params.id) }
            route("/series/add", exact = true) { seriesSearch() }
        }
    }
}

interface IdProps : RProps {
    var id: Long
}
