package aoc2023.day01

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class DayTest {

    private val year = 2023
    private val day = 1

    private val input = """1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet"""
    private val input2 = """two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen"""

    @Test
    fun exampleTest() {
        assertEquals(142,Day.part1(input.lines()))
    }
    @Test
    fun part1Test() {
        assertEquals(54388,useLines(year,day) { Day.part1(it.toList()) })
    }

    @Test
    fun exampleTest2() {
        assertEquals(281,Day.part2(input2.lines()))
    }
    @Test
    fun part2Test() {
        assertEquals(53515,useLines(year,day) { Day.part2(it.toList()) })
    }
}