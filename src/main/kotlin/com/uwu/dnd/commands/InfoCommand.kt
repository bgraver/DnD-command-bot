package com.uwu.dnd.commands

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class InfoCommand(val event:MessageReceivedEvent): DiscordCommand(event) {

  override fun execute() {
    if (event.author.id != event.jda.selfUser.id) {
      event.channel.sendMessage(
        infoMessage
      ).queue()
    }  }

  override fun validate(): Boolean = true

  companion object {
    val infoMessage = EmbedBuilder()
      .setTitle("Info")
      .addField("Info", """
        !info: shows info about the commands
      """.trimIndent(), false)
      .addField("Rolling", """
        !roll <1>d<2> <modifier>
            - (Example: [!roll 1d20 +6] rolls 1 20 sided die, with a modifier of +6)
            - <1> is the number of dice you'd like to roll
            - <2> the max number of the roll
            - <modifier> the modifier for the roll
      """.trimIndent(), false)
      .build()
  }
}
