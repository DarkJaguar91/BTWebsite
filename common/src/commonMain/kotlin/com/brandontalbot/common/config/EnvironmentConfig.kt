package com.brandontalbot.common.config

import com.brandontalbot.common.getEnv

open class EnvironmentConfig {
    open val debug by lazy {
        getEnv("DEBUG").toBoolean()
    }
}
