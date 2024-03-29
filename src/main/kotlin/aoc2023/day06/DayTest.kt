package aoc2023.day06

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class DayTest {

    private val year = 2023
    private val day = 6

    private val input = """Time:      7  15   30
Distance:  9  40  200"""
    @Test
    fun exampleTest() {
        assertEquals(288,Day.part1(input.lines()))
    }
    @Test
    fun part1Test() {
        assertEquals(840336,useLines(year,day) { Day.part1(it.toList()) })
    }

    @Test
    fun exampleTest2() {
        assertEquals(71503,Day.part2(input.lines()))
    }
    @Test
    fun part2Test() {
        assertEquals(41382569,useLines(year,day) { Day.part2(it.toList()) })
    }
}