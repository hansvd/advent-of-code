package aoc2022.day11

import org.junit.jupiter.api.Test
import shared.Input.getLines
import kotlin.test.assertEquals

class Day202211Test {

    @Test
    fun part1ExTest() {
        assertEquals(10605, getLines(2022,11, "ex") { Day202211.part1(it) })
    }

    @Test
    fun part1Test() {
        assertEquals(66124, getLines(2022,11) { Day202211.part1(it) })
    }


    @Test
    fun part2ExTest() {
        assertEquals(2713310158, getLines(2022,11, "ex") { Day202211.part2(it) })
    }

    @Test
    fun part2Test() {
        assertEquals(19309892877, getLines(2022,11) { Day202211.part2(it) })
    }
}