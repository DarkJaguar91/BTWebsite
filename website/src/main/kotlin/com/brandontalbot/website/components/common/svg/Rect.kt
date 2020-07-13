package com.brandontalbot.website.components.common.svg

import kotlinx.html.*
import react.dom.RDOMBuilder
import react.dom.tag
import styled.StyledDOMBuilder
import styled.styledTag

fun RDOMBuilder<SVG>.rect(classes: String? = null, block: RDOMBuilder<Rect>.() -> Unit) = tag(block) {
    Rect(attributesMapOf("classes", classes), it)
}

fun RDOMBuilder<Group>.rect(classes: String? = null, block: RDOMBuilder<Rect>.() -> Unit) = tag(block) {
    Rect(attributesMapOf("classes", classes), it)
}

fun RDOMBuilder<SVG>.rect(block: StyledDOMBuilder<Rect>.() -> Unit) = styledTag(block) {
    Rect(emptyMap, it)
}

fun RDOMBuilder<Group>.rect(block: StyledDOMBuilder<Rect>.() -> Unit) = styledTag(block) {
    Rect(emptyMap, it)
}

class Rect(initialAttributes: Map<String, String>, override val consumer: TagConsumer<*>) :
    HTMLTag("rect", consumer, initialAttributes, null, false, true),
    HtmlBlockInlineTag, SvgTag

var RDOMBuilder<Rect>.x by Attributes<Int>()
var RDOMBuilder<Rect>.y by Attributes<Int>()
var RDOMBuilder<Rect>.rx by Attributes<Int>()
var RDOMBuilder<Rect>.ry by Attributes<Int>()
var RDOMBuilder<Rect>.width by Attributes<Int>()
var RDOMBuilder<Rect>.height by Attributes<Int>()
var RDOMBuilder<Rect>.style by Attributes<String>()
