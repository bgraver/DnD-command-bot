package com.uwu.dnd.character

import com.uwu.dnd.dice.Roll
import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: String,
    val name: String,
    val modifiers: Map<Modifier, Int>,
    val actions: List<Action>
) {
   fun takeSavingThrow(modifier: Modifier): Roll {
       return modifiers[modifier]?.let {
           Roll(1, 20, mapOf(modifier to it))
       } ?: error("Modifier '$modifier' does not exist")
   }
}
