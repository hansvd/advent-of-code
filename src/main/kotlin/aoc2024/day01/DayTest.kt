package aoc2024.day01

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class DayTest {

    private val year = 2024
    private val day = 1

    private val input = """
        3   4
        4   3
        2   5
        1   3
        3   9
        3   3
    """.trimIndent()
    @Test
    fun exampleTest() {
        assertEquals(11,Day.part1(input.lines()))
    }
    @Test
    fun part1Test() {
        assertEquals(2164381,useLines(year,day) { Day.part1(it.toList()) })
    }

    @Test
    fun exampleTest2() {
        assertEquals(31,Day.part2(input.lines()))
    }
    @Test
    fun part2Test() {
        assertEquals(20719933,useLines(year,day) { Day.part2(it.toList()) })
    }
}