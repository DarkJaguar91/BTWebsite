package com.brandontalbot.djcommon.config

interface ConfigRetriever {
    fun getString(key: String): String?

    fun getLong(key: String): Long? = getString(key)?.toLong()

    fun getInt(key: String): Int? = getString(key)?.toInt()

    fun getBoolean(key: String): Boolean? = getString(key)?.toBoolean()
}
