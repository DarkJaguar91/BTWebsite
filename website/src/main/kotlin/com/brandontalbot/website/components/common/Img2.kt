package com.brandontalbot.website.components.common

import kotlinx.html.IMG
import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import react.RBuilder
import react.ReactElement
import react.dom.RDOMBuilder
import react.dom.tag

inline fun RBuilder.img(
    alt: String? = null,
    src: String? = null,
    classes: String? = null,
    loading: Loading? = null,
    block: RDOMBuilder<IMG>.() -> Unit = { }
): ReactElement = tag(block) {
    IMG(attributesMapOf("alt", alt, "src", src, "class", classes, "loading", loading?.name), it)
}

enum class Loading {
    lazy,
    eager
}
