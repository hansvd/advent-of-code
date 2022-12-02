package aoc2022.day02

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202203Test {

    @Test
    fun part1Test() {
        assertEquals(0,useLines(2022,3) { Day202204.part1(it) })
    }
    @Test
    fun part2Test() {
        assertEquals(0,useLines(2022,3) { Day202204.part2(it) })
    }
}