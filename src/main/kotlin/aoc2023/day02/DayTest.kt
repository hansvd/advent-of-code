package aoc2023.day02

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class DayTest {

    private val year = 2023
    private val day = 2

    private val input = """Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"""

    @Test
    fun exampleTest() {
        assertEquals(8,Day.part1(input.lines()))
    }
    @Test
    fun part1Test() {
        assertEquals(2447,useLines(year,day) { Day.part1(it.toList()) })
    }

    @Test
    fun exampleTest2() {
        assertEquals(2286,Day.part2(input.lines()))
    }
    @Test
    fun part2Test() {
        assertEquals(56322,useLines(year,day) { Day.part2(it.toList()) })
    }
}