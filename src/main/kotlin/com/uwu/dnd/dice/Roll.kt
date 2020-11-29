package com.uwu.dnd.dice

import com.uwu.dnd.character.Modifier
import com.uwu.dnd.exceptions.RegexParseException
import kotlinx.serialization.Serializable
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.text.ParseException
import kotlin.jvm.Throws
import kotlin.random.Random
import kotlin.random.nextInt

@Serializable
data class Roll(
    private val numberOfDice: Int,
    private val numberOfSides: Int,
    private val modifiers: Map<Modifier, Int>
) {

    private val logger: Logger = LoggerFactory.getLogger(Roll::class.java)

    fun getResult(): Int {
        return (1..numberOfDice).map {
            Random.nextInt(1..numberOfSides)
        }.sum() + modifiers.values.sum()
    }

    fun getResultString(): String {
        val diceRoll = (1..numberOfDice).map {
            Random.nextInt(1..numberOfSides)
        }.sum()
        val modifiersString = modifiers.map { (t, u) -> if (t != Modifier.RAW) "$t($u)" else "($u)" }

        return "$diceRoll+${modifiersString.joinToString("+") { it }}=${diceRoll + modifiers.values.sum()}"
    }


    companion object {
        private val rollRegex =
            Regex("\\[(\\d+)d(\\d+)(([\\+|\\-](STRENGTH|DEXTERITY|CONSTITUTION|INTELLIGENCE|WISDOM|CHARISMA|-?\\d+))*)\\]")

        @Throws(RegexParseException::class)
        fun parseFromString(s: String): List<Roll> {
            val matches = rollRegex.findAll(s)

            return matches.map { match: MatchResult ->
                val groups = match.groups as? MatchNamedGroupCollection
                    ?: error("Could not match regex to named capture group $match")
                return@map Roll(
                    groups[1]?.value?.toInt()!!,
                    groups[2]?.value?.toInt()!!,
                    groups[3]?.value?.split("[\\+|\\-]".toRegex())?.filter { it.isNotBlank() }?.map {
                        translateModifierToInt(it, groups[3]!!.value.take(1))
                    }?.toMap() ?: throw RegexParseException("Regex was not processed properly with groups: '$groups'")
                )
            }.toList()
        }

        @Throws(RegexParseException::class)
        private fun translateModifierToInt(s: String, plusOrMinus: String): Pair<Modifier, Int> {
            return when {
                Modifier.values().any { it.name == s } -> Modifier.valueOf(s) to 0
                Modifier.values().any { it.short == s } -> Modifier.values().find { it.short == s }!! to 0
                else -> Modifier.RAW to (when (plusOrMinus) {
                    "+" -> s.toIntOrNull()
                    "-" -> s.toIntOrNull()?.unaryMinus()
                    else -> throw RegexParseException("Could not parse sign ")
                } ?: throw RegexParseException("Could not map '$s' to modifier pair"))
            }
        }
    }


    override fun equals(other: Any?): Boolean {
        if (other !is Roll) return false

        return other.numberOfDice == this.numberOfDice &&
                other.numberOfSides == this.numberOfSides &&
                other.modifiers == this.modifiers
    }

    override fun hashCode(): Int {
        var result = numberOfDice
        result = 31 * result + numberOfSides
        result = 31 * result + modifiers.hashCode()
        return result
    }

}
