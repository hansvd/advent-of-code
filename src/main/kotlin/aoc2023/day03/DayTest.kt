package aoc2023.day03

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class DayTest {

    private val year = 2023
    private val day = 3

    private val input =
         """467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...${'$'}.*....
.664.598.."""


    @Test
    fun exampleTest() {
        assertEquals(4361,Day.part1(input.lines()))
    }
    @Test
    fun part1Test() {
        assertEquals(551094,useLines(year,day) { Day.part1(it.toList()) })
    }

    @Test
    fun exampleTest2() {
        assertEquals(467835,Day.part2(input.lines()))
    }
    @Test
    fun part2Test() {
        assertEquals(80179647,useLines(year,day) { Day.part2(it.toList()) })
    }
}