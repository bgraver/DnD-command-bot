package com.uwu.dnd.character

import com.uwu.dnd.dice.Roll
import kotlinx.serialization.Serializable

@Serializable
data class Action(
    val name: String,
    val range: Int,
    val accuracy: Roll,
    val damage: Roll,
    val notes: String
) {
}
