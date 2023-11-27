package aoc2023.day00

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class DayTest {

    val year = 2023
    val day = 0

    val input = ""
    @Test
    fun exampleTest() {
        assertEquals(0,Day.part1(input.lines()))
    }
    @Test
    fun part1Test() {
        assertEquals(0,useLines(year,day) { Day.part1(it.toList()) })
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