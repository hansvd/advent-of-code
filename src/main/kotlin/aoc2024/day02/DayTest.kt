package aoc2024.day02

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class DayTest {

    private val year = 2024
    private val day = 2

    private val input = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """.trimIndent()
    @Test
    fun exampleTest() {
        assertEquals(2,Day.part1(input.lines()))
    }
    @Test
    fun part1Test() {
        assertEquals(402,useLines(year,day) { Day.part1(it.toList()) })
    }

    @Test
    fun exampleTest2() {
        assertEquals(4,Day.part2(input.lines()))
    }
    @Test
    fun part2Test() {
        assertEquals(455,useLines(year,day) { Day.part2(it.toList()) })
    }
}