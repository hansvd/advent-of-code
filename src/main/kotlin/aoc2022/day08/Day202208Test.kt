package aoc2022.day08

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202208Test {

    val exInput = """30373
25512
65332
33549
35390"""

    @Test
    fun part1ExTest() {
        assertEquals(21, Day202208.part1(exInput.lineSequence()))
    }

    @Test
    fun part1Test() {
        assertEquals(1812,useLines(2022,8) { Day202208.part1(it) })
    }


    @Test
    fun part2ExTest() {
        assertEquals(8, Day202208.part2(exInput.lineSequence()))
    }

    @Test
    fun part2Test() {
        assertEquals(315495,useLines(2022,8) { Day202208.part2(it) })
    }
}