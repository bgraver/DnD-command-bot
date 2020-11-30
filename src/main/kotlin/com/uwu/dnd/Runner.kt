package com.uwu.dnd

import com.uwu.dnd.commands.InfoCommand
import com.uwu.dnd.commands.RollCommand
import com.uwu.dnd.commands.TextParseCommand
import com.uwu.dnd.exceptions.EnvironmentVariableNotFoundException
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.slf4j.Logger
import org.slf4j.LoggerFactory

private val logger: Logger = LoggerFactory.getLogger("MainClass")

fun main() {
    val token: String = System.getenv("BOT_TOKEN")
        ?: throw EnvironmentVariableNotFoundException("BOT_TOKEN")
    startBot(token)
}


fun startBot(token: String): JDA {
    return JDABuilder.createDefault(token)
        .addEventListeners(MessageListener())
        .build()
        .awaitReady()
}

class MessageListener : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        try {
            val author = event.author
            val message = event.message

            val msg = message.contentDisplay

            if (!author.isBot) {
                val words = msg.split("\\s+".toRegex())

                val command = when (words[0]) {
                    "!roll" -> RollCommand(event)
                    "!info" -> InfoCommand(event)
                    else -> TextParseCommand(event)
                }
                if (command.validate()) {
                    command.execute()
                } else {
                    logger.error("Message is invalid '$msg'")
                }
            }
        } catch (e: Exception) {
            logger.error("Handling of message '${event.message.contentRaw}' failed with error ${e.stackTrace}")
        }
    }
}


