package com.brandontalbot.website.components.common.svg

import kotlinx.html.*
import react.dom.RDOMBuilder
import react.dom.tag
import styled.StyledDOMBuilder
import styled.styledTag

fun RDOMBuilder<SVG>.text(classes: String? = null, block: RDOMBuilder<Text>.() -> Unit) = tag(block) {
    Text(attributesMapOf("classes", classes), it)
}

fun RDOMBuilder<Group>.text(classes: String? = null, block: RDOMBuilder<Text>.() -> Unit) = tag(block) {
    Text(attributesMapOf("classes", classes), it)
}

fun RDOMBuilder<SVG>.text(block: StyledDOMBuilder<Text>.() -> Unit) = styledTag(block) {
    Text(emptyMap, it)
}

fun RDOMBuilder<Group>.text(block: StyledDOMBuilder<Text>.() -> Unit) = styledTag(block) {
    Text(emptyMap, it)
}

class Text(initialAttributes: Map<String, String>, override val consumer: TagConsumer<*>) :
    HTMLTag("text", consumer, initialAttributes, null, false, false),
    HtmlBlockInlineTag, SvgTag

var RDOMBuilder<Text>.x by Attributes<Int>()
var RDOMBuilder<Text>.y by Attributes<Int>()
var RDOMBuilder<Text>.rotate by Attributes<Int>()
var RDOMBuilder<Text>.textLength by Attributes<Int>()
var RDOMBuilder<Text>.fontSize by Attributes<Int>()

