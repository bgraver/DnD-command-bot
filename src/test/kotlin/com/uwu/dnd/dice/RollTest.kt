package com.uwu.dnd.dice

import com.uwu.dnd.character.Modifier
import org.testng.Assert.assertEquals
import org.testng.annotations.Test


class RollTest {

    @Test
    fun testPattern() {
        val roll = Roll.parseFromString("I want to do [1d20+STRENGTH]")

        assertEquals(
            roll.first(),
            Roll(1, 20, mapOf(Modifier.STRENGTH to 0))
        )
    }

    @Test
    fun testMultipleRolls() {
        val rolls = Roll.parseFromString("Rolling for accuracy: [1d20+DEXTERITY], damage: [2d4+STRENGTH]")

        assertEquals(
            rolls,
            listOf(
                Roll(1, 20, mapOf(Modifier.DEXTERITY to 0)),
                Roll(2, 4, mapOf(Modifier.STRENGTH to 0))
            )
        )
    }

    @Test
    fun testMultipleModifiers() {
        val roll = Roll.parseFromString("Rolling for damage: [1d10+WISDOM+CHARISMA]")

        assertEquals(
            roll.first(),
            Roll(1, 10, mapOf(Modifier.WISDOM to 0, Modifier.CHARISMA to 0))
        )
    }

    @Test
    fun testRawNumber() {
        val roll = Roll.parseFromString("Rolling for damage: [1d10+2]")

        assertEquals(
            roll.first(),
            Roll(1, 10, mapOf(Modifier.RAW to 2))
        )
    }

    @Test
    fun testRawBigNumber() {
        val roll = Roll.parseFromString("Rolling for damage: [1d10+22]")

        assertEquals(
            roll.first(),
            Roll(1, 10, mapOf(Modifier.RAW to 22))
        )
    }

    @Test
    fun testRawSubtraction() {
        val roll = Roll.parseFromString("Rolling for damage: [1d10-1]")

        assertEquals(
            roll.first(),
            Roll(1, 10, mapOf(Modifier.RAW to -1))
        )
    }

//    @Test
//    fun testRawSubtractionAlternateSyntax() {
//        val roll = Roll.parseFromString("Rolling for damage: [1d10+-1]")
//
//        assertEquals(
//            roll.first(),
//            Roll(1, 10, mapOf(Modifier.RAW to -1))
//        )
//    }
}
