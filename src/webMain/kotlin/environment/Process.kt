package environment

external val process: Process

external interface Process {
    val env: dynamic
}
