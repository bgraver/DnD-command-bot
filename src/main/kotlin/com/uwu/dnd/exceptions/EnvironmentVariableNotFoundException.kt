package com.uwu.dnd.exceptions

class EnvironmentVariableNotFoundException : RuntimeException {
    constructor() : super()
    constructor(variableName: String) : super("$variableName not found")
    constructor(variableName: String, cause: Throwable) : super("$variableName not found", cause)
    constructor(cause: Throwable) : super(cause)
}
