package aoc2022.day25

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202225Test {

    val exInput = """"""

    @Test
    fun part1ExTest() {
        assertEquals(0, Day202225.part1(exInput.lineSequence()))
    }

    @Test
    fun part1Test() {
        assertEquals(0,useLines(2022,25) { Day202225.part1(it) })
    }


    @Test
    fun part2ExTest() {
        assertEquals(0, Day202225.part2(exInput.lineSequence()))
    }

    @Test
    fun part2Test() {
        assertEquals(0,useLines(2022,25) { Day202225.part2(it) })
    }
}