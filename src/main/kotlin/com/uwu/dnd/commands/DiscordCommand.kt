package com.uwu.dnd.commands

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class DiscordCommand(protected val event: MessageReceivedEvent) {

    protected val logger: Logger = LoggerFactory.getLogger(this::class.java)

    abstract fun execute()

    abstract fun validate(): Boolean

}
