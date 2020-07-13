package com.brandontalbot.website.components.common.svg

import kotlinx.html.*
import react.dom.RDOMBuilder
import react.dom.tag
import styled.StyledDOMBuilder
import styled.styledTable
import styled.styledTag

fun RDOMBuilder<SVG>.g(classes: String? = null, block: RDOMBuilder<Group>.() -> Unit) = tag(block) {
    Group(attributesMapOf("classes", classes), it)
}

fun RDOMBuilder<Group>.g(classes: String? = null, block: RDOMBuilder<Group>.() -> Unit) = tag(block) {
    Group(attributesMapOf("classes", classes), it)
}

fun RDOMBuilder<SVG>.styledG(block: StyledDOMBuilder<Group>.() -> Unit) = styledTag(block) {
    Group(emptyMap, it)
}

fun RDOMBuilder<Group>.styledG(block: StyledDOMBuilder<Group>.() -> Unit) = styledTag(block) {
    Group(emptyMap, it)
}

class Group(initialAttributes: Map<String, String>, override val consumer: TagConsumer<*>) :
    HTMLTag("g", consumer, initialAttributes, null, false, false),
    HtmlBlockInlineTag, SvgTag

