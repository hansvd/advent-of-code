package aoc2022.day24

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202224Test {

    val exInput = """#.#####
#.....#
#>....#
#.....#
#...v.#
#.....#
#####.#"""

    val exInput2 = """#.######
#>>.<^<#
#.<..<<#
#>v.><>#
#<^v^^>#
######.#"""

    @Test
    fun part1ExTest() {
        assertEquals(10, Day202224.part1(exInput.lineSequence()))
    }

    @Test
    fun part1Ex2Test() {
        assertEquals(18, Day202224.part1(exInput2.lineSequence()))
    }

    @Test
    fun part1Test() {
        assertEquals(297,useLines(2022,24) { Day202224.part1(it) })
    }


    @Test
    fun part2ExTest() {
        assertEquals(0, Day202224.part2(exInput.lineSequence()))
    }

    @Test
    fun part2Test() {
        assertEquals(0,useLines(2022,23) { Day202224.part2(it) })
    }
}