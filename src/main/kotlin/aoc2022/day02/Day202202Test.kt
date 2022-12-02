package aoc2022.day02

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

val testInput = """A Y
B X
C Z"""

class Day202202Test {

    @Test
    fun part1ExTest() {
        assertEquals(15,Day202202.part1(testInput.lineSequence()) )
    }

    @Test
    fun part1Test() {
        assertEquals(11841,useLines(2022,2) { Day202202.part1(it) })
    }

    @Test
    fun part2ExTest() {
        assertEquals(12,Day202202.part2(testInput.lineSequence()) )
    }

    @Test
    fun part2Test() {
        assertEquals(13022,useLines(2022,2) { Day202202.part2(it) })
    }
}