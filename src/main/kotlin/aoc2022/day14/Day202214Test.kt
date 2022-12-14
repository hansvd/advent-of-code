package aoc2022.day14

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202214Test {

    val exInput = """498,4 -> 498,6 -> 496,6
503,4 -> 502,4 -> 502,9 -> 494,9"""

    @Test
    fun part1ExTest() {
        assertEquals(24, Day202214.part1(exInput.lineSequence()))
    }

    @Test
    fun part1Test() {
        assertEquals(873,useLines(2022,14) { Day202214.part1(it) })
    }


    @Test
    fun part2ExTest() {
        assertEquals(93, Day202214.part2(exInput.lineSequence()))
    }

    @Test
    fun part2Test() {
        assertEquals(24813,useLines(2022,14) { Day202214.part2(it) })
    }
}