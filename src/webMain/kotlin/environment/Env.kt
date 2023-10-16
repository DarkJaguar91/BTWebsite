package environment

fun <R> isProd(block: () -> R?) = if (process.env.NODE_ENV == "production") block() else null
