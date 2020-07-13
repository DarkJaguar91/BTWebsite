package com.brandontalbot.website.components.common

import kotlinx.css.*
import styled.StyleSheet

@Suppress("unused")
object GridStyles : StyleSheet("GridSystem") {
    val container by css {
        width = 100.pct
        display = Display.flex
        flexWrap = FlexWrap.wrap
    }

    val col1 by css {
        +colCommon
        flex(0.0, 0.0, 8.3333333333.pct)
        maxWidth = 8.3333333333.pct
    }

    val col2 by css {
        +colCommon
        flex(0.0, 0.0, 16.6666666667.pct)
        maxWidth = 16.6666666667.pct
    }

    val col3 by css {
        +colCommon
        flex(0.0, 0.0, 25.pct)
        maxWidth = 25.pct
    }

    val col4 by css {
        +colCommon
        flex(0.0, 0.0, 33.3333333333.pct)
        maxWidth = 33.3333333333.pct
    }

    val col5 by css {
        +colCommon
        flex(0.0, 0.0, 41.6666666667.pct)
        maxWidth = 41.6666666667.pct
    }

    val col6 by css {
        +colCommon
        flex(0.0, 0.0, 50.pct)
        maxWidth = 50.pct
    }

    val col7 by css {
        +colCommon
        flex(0.0, 0.0, 58.3333333333.pct)
        maxWidth = 58.3333333333.pct
    }

    val col8 by css {
        +colCommon
        flex(0.0, 0.0, 66.6666666667.pct)
        maxWidth = 66.6666666667.pct
    }

    val col9 by css {
        +colCommon
        flex(0.0, 0.0, 75.pct)
        maxWidth = 75.pct
    }

    val col10 by css {
        +colCommon
        flex(0.0, 0.0, 83.3333333333.pct)
        maxWidth = 83.3333333333.pct
    }

    val col11 by css {
        +colCommon
        flex(0.0, 0.0, 91.6666666667.pct)
        maxWidth = 91.6666666667.pct
    }

    val col12 by css {
        +colCommon
        flex(0.0, 0.0, 100.pct)
        maxWidth = 100.pct
    }

    val colSm1 by css {
        +colCommon

        media("(min-width 576px)") {
            flex(0.0, 0.0, 8.3333333333.pct)
            maxWidth = 8.3333333333.pct
        }
    }

    val colSm2 by css {
        +colCommon

        media("(min-width 576px)") {
            flex(0.0, 0.0, 16.6666666667.pct)
            maxWidth = 16.6666666667.pct
        }
    }

    val colSm3 by css {
        +colCommon

        media("(min-width 576px)") {
            flex(0.0, 0.0, 25.pct)
            maxWidth = 25.pct
        }
    }

    val colSm4 by css {
        +colCommon

        media("(min-width 576px)") {
            flex(0.0, 0.0, 33.3333333333.pct)
            maxWidth = 33.3333333333.pct
        }
    }

    val colSm5 by css {
        +colCommon

        media("(min-width 576px)") {
            flex(0.0, 0.0, 41.6666666667.pct)
            maxWidth = 41.6666666667.pct
        }
    }

    val colSm6 by css {
        +colCommon

        media("(min-width 576px)") {
            flex(0.0, 0.0, 50.pct)
            maxWidth = 50.pct
        }
    }

    val colSm7 by css {
        +colCommon

        media("(min-width 576px)") {
            flex(0.0, 0.0, 58.3333333333.pct)
            maxWidth = 58.3333333333.pct
        }
    }

    val colSm8 by css {
        +colCommon

        media("(min-width 576px)") {
            flex(0.0, 0.0, 66.6666666667.pct)
            maxWidth = 66.6666666667.pct
        }
    }

    val colSm9 by css {
        +colCommon

        media("(min-width 576px)") {
            flex(0.0, 0.0, 75.pct)
            maxWidth = 75.pct
        }
    }

    val colSm10 by css {
        +colCommon

        media("(min-width 576px)") {
            flex(0.0, 0.0, 83.3333333333.pct)
            maxWidth = 83.3333333333.pct
        }
    }

    val colSm11 by css {
        +colCommon

        media("(min-width 576px)") {
            flex(0.0, 0.0, 91.6666666667.pct)
            maxWidth = 91.6666666667.pct
        }
    }

    val colSm12 by css {
        +colCommon

        media("(min-width 576px)") {
            flex(0.0, 0.0, 100.pct)
            maxWidth = 100.pct
        }
    }

    val colMd1 by css {
        +colCommon

        media("(min-width 768px)") {
            flex(0.0, 0.0, 8.3333333333.pct)
            maxWidth = 8.3333333333.pct
        }
    }

    val colMd2 by css {
        +colCommon

        media("(min-width 768px)") {
            flex(0.0, 0.0, 16.6666666667.pct)
            maxWidth = 16.6666666667.pct
        }
    }

    val colMd3 by css {
        +colCommon

        media("(min-width 768px)") {
            flex(0.0, 0.0, 25.pct)
            maxWidth = 25.pct
        }
    }

    val colMd4 by css {
        +colCommon

        media("(min-width 768px)") {
            flex(0.0, 0.0, 33.3333333333.pct)
            maxWidth = 33.3333333333.pct
        }
    }

    val colMd5 by css {
        +colCommon

        media("(min-width 768px)") {
            flex(0.0, 0.0, 41.6666666667.pct)
            maxWidth = 41.6666666667.pct
        }
    }

    val colMd6 by css {
        +colCommon

        media("(min-width 768px)") {
            flex(0.0, 0.0, 50.pct)
            maxWidth = 50.pct
        }
    }

    val colMd7 by css {
        +colCommon

        media("(min-width 768px)") {
            flex(0.0, 0.0, 58.3333333333.pct)
            maxWidth = 58.3333333333.pct
        }
    }

    val colMd8 by css {
        +colCommon

        media("(min-width 768px)") {
            flex(0.0, 0.0, 66.6666666667.pct)
            maxWidth = 66.6666666667.pct
        }
    }

    val colMd9 by css {
        +colCommon

        media("(min-width 768px)") {
            flex(0.0, 0.0, 75.pct)
            maxWidth = 75.pct
        }
    }

    val colMd10 by css {
        +colCommon

        media("(min-width 768px)") {
            flex(0.0, 0.0, 83.3333333333.pct)
            maxWidth = 83.3333333333.pct
        }
    }

    val colMd11 by css {
        +colCommon

        media("(min-width 768px)") {
            flex(0.0, 0.0, 91.6666666667.pct)
            maxWidth = 91.6666666667.pct
        }
    }

    val colMd12 by css {
        +colCommon

        media("(min-width 768px)") {
            flex(0.0, 0.0, 100.pct)
            maxWidth = 100.pct
        }
    }

    val colLg1 by css {
        +colCommon

        media("(min-width 992px)") {
            flex(0.0, 0.0, 8.3333333333.pct)
            maxWidth = 8.3333333333.pct
        }
    }

    val colLg2 by css {
        +colCommon

        media("(min-width 992px)") {
            flex(0.0, 0.0, 16.6666666667.pct)
            maxWidth = 16.6666666667.pct
        }
    }

    val colLg3 by css {
        +colCommon

        media("(min-width 992px)") {
            flex(0.0, 0.0, 25.pct)
            maxWidth = 25.pct
        }
    }

    val colLg4 by css {
        +colCommon

        media("(min-width 992px)") {
            flex(0.0, 0.0, 33.3333333333.pct)
            maxWidth = 33.3333333333.pct
        }
    }

    val colLg5 by css {
        +colCommon

        media("(min-width 992px)") {
            flex(0.0, 0.0, 41.6666666667.pct)
            maxWidth = 41.6666666667.pct
        }
    }

    val colLg6 by css {
        +colCommon

        media("(min-width 992px)") {
            flex(0.0, 0.0, 50.pct)
            maxWidth = 50.pct
        }
    }

    val colLg7 by css {
        +colCommon

        media("(min-width 992px)") {
            flex(0.0, 0.0, 58.3333333333.pct)
            maxWidth = 58.3333333333.pct
        }
    }

    val colLg8 by css {
        +colCommon

        media("(min-width 992px)") {
            flex(0.0, 0.0, 66.6666666667.pct)
            maxWidth = 66.6666666667.pct
        }
    }

    val colLg9 by css {
        +colCommon

        media("(min-width 992px)") {
            flex(0.0, 0.0, 75.pct)
            maxWidth = 75.pct
        }
    }

    val colLg10 by css {
        +colCommon

        media("(min-width 992px)") {
            flex(0.0, 0.0, 83.3333333333.pct)
            maxWidth = 83.3333333333.pct
        }
    }

    val colLg11 by css {
        +colCommon

        media("(min-width 992px)") {
            flex(0.0, 0.0, 91.6666666667.pct)
            maxWidth = 91.6666666667.pct
        }
    }

    val colLg12 by css {
        +colCommon

        media("(min-width 992px)") {
            flex(0.0, 0.0, 100.pct)
            maxWidth = 100.pct
        }
    }


    val colXl1 by css {
        +colCommon

        media("(min-width 1200px)") {
            flex(0.0, 0.0, 8.3333333333.pct)
            maxWidth = 8.3333333333.pct
        }
    }

    val colXl2 by css {
        +colCommon

        media("(min-width 1200px)") {
            flex(0.0, 0.0, 16.6666666667.pct)
            maxWidth = 16.6666666667.pct
        }
    }

    val colXl3 by css {
        +colCommon

        media("(min-width 1200px)") {
            flex(0.0, 0.0, 25.pct)
            maxWidth = 25.pct
        }
    }

    val colXl4 by css {
        +colCommon

        media("(min-width 1200px)") {
            flex(0.0, 0.0, 33.3333333333.pct)
            maxWidth = 33.3333333333.pct
        }
    }

    val colXl5 by css {
        +colCommon

        media("(min-width 1200px)") {
            flex(0.0, 0.0, 41.6666666667.pct)
            maxWidth = 41.6666666667.pct
        }
    }

    val colXl6 by css {
        +colCommon

        media("(min-width 1200px)") {
            flex(0.0, 0.0, 50.pct)
            maxWidth = 50.pct
        }
    }

    val colXl7 by css {
        +colCommon

        media("(min-width 1200px)") {
            flex(0.0, 0.0, 58.3333333333.pct)
            maxWidth = 58.3333333333.pct
        }
    }

    val colXl8 by css {
        +colCommon

        media("(min-width 1200px)") {
            flex(0.0, 0.0, 66.6666666667.pct)
            maxWidth = 66.6666666667.pct
        }
    }

    val colXl9 by css {
        +colCommon

        media("(min-width 1200px)") {
            flex(0.0, 0.0, 75.pct)
            maxWidth = 75.pct
        }
    }

    val colXl10 by css {
        +colCommon

        media("(min-width 1200px)") {
            flex(0.0, 0.0, 83.3333333333.pct)
            maxWidth = 83.3333333333.pct
        }
    }

    val colXl11 by css {
        +colCommon

        media("(min-width 1200px)") {
            flex(0.0, 0.0, 91.6666666667.pct)
            maxWidth = 91.6666666667.pct
        }
    }

    val colXl12 by css {
        +colCommon

        media("(min-width 1200px)") {
            flex(0.0, 0.0, 100.pct)
            maxWidth = 100.pct
        }
    }
    private val colCommon by css {
        position = Position.relative
        width = 100.pct
    }
}