package aoc2022.day13

import org.junit.jupiter.api.Test
import shared.Input.getLines
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202213Test {


    @Test
    fun part1ExTest() {
        assertEquals(13, getLines(2022,13, "ex") { Day202213.part1(it) })
    }

    @Test
    fun part1Test() {
        assertEquals(5252, getLines(2022,13) { Day202213.part1(it) })
    }


    @Test
    fun part2ExTest() {
        assertEquals(140, useLines(2022,13, "ex") { Day202213.part2(it) })
    }

    @Test
    fun part2Test() {
        assertEquals(20592,useLines(2022,13) { Day202213.part2(it) })
    }
}