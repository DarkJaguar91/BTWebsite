package com.brandontalbot.common

import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.delay
import kotlin.reflect.KClass
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

expect fun getEnv(name: String): String?

@OptIn(ExperimentalTime::class)
suspend fun <T> retryIo(
    exceptions: List<KClass<*>> = listOf(IOException::class),
    retries: Int = 3,
    initialDelay: Duration = 100.toDuration(DurationUnit.MILLISECONDS),
    maxDelay: Duration = 1.toDuration(DurationUnit.SECONDS),
    factor: Double = 2.toDouble(),
    block: suspend () -> T
): T {
    var currentDelay = initialDelay.toLongMilliseconds()
    repeat(retries - 1) {
        try {
            return block()
        } catch (e: Throwable) {
            if (e::class !in exceptions) {
                throw e
            }
        }
        delay(currentDelay)
        currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay.toLongMilliseconds())
    }
    return block()
}
