package aoc2023.day07

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class DayTest {

    private val year = 2023
    private val day = 7

    private val input = """32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483"""
    @Test
    fun exampleTest() {
        assertEquals(6440,DayPart1.part1(input.lines()))
    }
    @Test
    fun part1Test() {
        assertEquals(250602641,useLines(year,day) { DayPart1.part1(it.toList()) })
    }

    @Test
    fun exampleTest2() {
        assertEquals(5905,DayPart2.part2(input.lines()))
    }
    @Test
    fun part2Test() {
        assertEquals(251037509,useLines(year,day) { DayPart2.part2(it.toList()) })
    }
}