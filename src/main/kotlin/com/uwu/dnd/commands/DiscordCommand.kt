package com.uwu.dnd.commands

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

abstract class DiscordCommand(protected val event: MessageReceivedEvent) {

    abstract fun execute()

    abstract fun validate(): Boolean

}
