package aoc2022.day12

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202212Test {

    val exInput = """Sabqponm
abcryxxl
accszExk
acctuvwj
abdefghi"""

    @Test
    fun part1ExTest() {
        assertEquals(31, Day202212.part1(exInput.lineSequence()))
    }

    @Test
    fun part1Test() {
        assertEquals(437, useLines(2022, 12) { Day202212.part1(it) })
    }


    @Test
    fun part2ExTest() {
        assertEquals(29, Day202212.part2(exInput.lineSequence()))
    }

    @Test
    fun part2Test() {
        assertEquals(430, useLines(2022, 12) { Day202212.part2(it) })
    }
}