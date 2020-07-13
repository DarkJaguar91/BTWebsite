package com.brandontalbot.website.components.common.svg

import kotlinx.css.Color
import kotlinx.html.*
import react.ReactElement
import react.dom.RDOMBuilder
import react.dom.tag
import styled.StyledDOMBuilder
import styled.styledTag

inline fun RDOMBuilder<SVG>.circle(classes: String? = null, block: RDOMBuilder<Circle>.() -> Unit): ReactElement =
    tag(block) {
        Circle(attributesMapOf("classes", classes), it)
    }

inline fun RDOMBuilder<Group>.circle(classes: String? = null, block: RDOMBuilder<Circle>.() -> Unit): ReactElement =
    tag(block) {
        Circle(attributesMapOf("classes", classes), it)
    }

inline fun StyledDOMBuilder<SVG>.styledCircle(block: StyledDOMBuilder<Circle>.() -> Unit): ReactElement =
    styledTag(block) {
        Circle(emptyMap, it)
    }

inline fun RDOMBuilder<Group>.styledCircle(
    classes: String? = null,
    block: StyledDOMBuilder<Circle>.() -> Unit
): ReactElement =
    styledTag(block) {
        Circle(attributesMapOf("classes", classes), it)
    }


class Circle(initialAttributes: Map<String, String>, override val consumer: TagConsumer<*>) :
    HTMLTag("circle", consumer, initialAttributes, null, false, true),
    HtmlBlockInlineTag, SvgTag

var RDOMBuilder<Circle>.cx by Attributes<Int>()
var RDOMBuilder<Circle>.cy by Attributes<Int>()
var RDOMBuilder<Circle>.r by Attributes<Int>()
