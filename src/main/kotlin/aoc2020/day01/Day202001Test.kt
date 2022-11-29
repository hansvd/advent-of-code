package aoc2020.day01

import org.junit.jupiter.api.Test
import shared.Input
import kotlin.test.assertEquals

class Day202001Test {
    val input = """
        1721
        979
        366
        299
        675
        1456""".trimIndent()

    @Test
    fun testAdvent1example() {
        assertEquals(514579, Day202001.part01(input.lineSequence()))
    }
    @Test
    fun testAdvent1() {
        assertEquals(806656, Input.useLines(2020,1) { Day202001.part01(it) })
    }
    @Test
    fun testAdvent1example2() {
        assertEquals(241861950, Day202001.part02(input.lineSequence()))
    }
    @Test
    fun testAdvent1Part2() {
        assertEquals(230608320, Input.useLines(2020,1) { Day202001.part02(it) })
    }
}