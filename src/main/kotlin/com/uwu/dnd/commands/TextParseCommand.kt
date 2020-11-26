package com.uwu.dnd.commands

import com.uwu.dnd.dice.Roll
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class TextParseCommand(event: MessageReceivedEvent) : DiscordCommand(event) {

    override fun execute() {
        val rolls = Roll.parseFromString(event.message.contentRaw)
            .mapIndexed { index, roll ->
                "Roll #${index + 1}: ${roll.getResultString()}\n"
            }.joinToString { it }

        if (rolls.isNotBlank()) {
            event.channel.sendMessage(rolls).complete()
        }
    }

    override fun validate(): Boolean {
        return true
    }

}
