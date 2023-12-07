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
        assertEquals(6440,Day.part1(input.lines()))
    }
    @Test
    fun part1Test() {
        assertEquals(250602641,useLines(year,day) { Day.part1(it.toList()) })
    }

    @Test
    fun exampleTest2() {
        assertEquals(0,Day.part2(input.lines()))
    }
    @Test
    fun part2Test() {
        assertEquals(0,useLines(year,day) { Day.part2(it.toList()) })
    }
}