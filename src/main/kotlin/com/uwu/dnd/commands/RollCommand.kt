package com.uwu.dnd.commands
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import kotlin.random.Random

class RollCommand(val event: MessageReceivedEvent): DiscordCommand(event) {
  /*
  Syntax:
  !roll 1d20 +n
   */

  override fun execute() {
    val author = event.author
    val words = event.message.contentDisplay.split(" ")

    val roll = words[1].split("d")

    val modifier: Int = if (words.size == 3) {
      words[2].replace("+", "").toInt()
    } else {
      0
    }

    var output = 0
    val dice = roll[0].toInt()
    var replyString = ""

    for(i in 1..dice)
    {
      val r = roll(maximum = roll[1].toInt())
      output += r
      replyString += "${author.name} rolled a $r\n"
    }

    replyString += "Out of $dice, ${author.name} rolled a ${output+modifier}"
    event.channel.sendMessage(replyString).queue()

  }

  override fun validate(): Boolean {
    val words = event.message.contentDisplay.split(" ")
    return words.size == 2 || words.size == 3
  }

  private fun roll(minimum: Int = 1, maximum: Int = 20, modifier: Int = 0): Int {
    return Random.nextInt(minimum, maximum) + modifier
  }
}
