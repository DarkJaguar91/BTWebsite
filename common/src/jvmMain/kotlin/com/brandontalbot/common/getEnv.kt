package com.brandontalbot.common

actual fun getEnv(name: String): String? = System.getenv(name)
