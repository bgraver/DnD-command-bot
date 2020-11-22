package com.uwu.dnd
import com.fasterxml.jackson.databind.ObjectMapper
import com.uwu.dnd.commands.RollCommand
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent

fun main() {
    println("Hello!")
    val token: String = System.getenv("BOT_TOKEN") ?: throw Exception("BOT_TOKEN environment variable required")
    startBot(token)
}


fun startBot(token: String): JDA {
  return JDABuilder.createDefault(token)
    .addEventListeners(MessageListener())
    .build()
    .awaitReady()
}

class MessageListener: ListenerAdapter() {
  override fun onMessageReceived(event: MessageReceivedEvent) {
    val author = event.author
    val message = event.message
    val channel = event.channel

    val msg = message.contentDisplay

    val words = msg.split(" ")

    val command = when (words[0]) {
      "!roll" -> RollCommand(event)
      else -> return
    }
  }
}


