package com.uwu.dnd.commands

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

abstract class DiscordCommand(event: MessageReceivedEvent) {

    abstract fun execute()

    abstract fun validate(): Boolean

}
