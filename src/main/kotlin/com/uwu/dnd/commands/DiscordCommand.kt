package com.uwu.dnd.commands

abstract class DiscordCommand {

    abstract fun execute()

    abstract fun validate(): Boolean

}
