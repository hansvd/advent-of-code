package aoc2022.day02

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day2022xxTest {

    val exInput = """"""

    @Test
    fun part1ExTest() {
        assertEquals(0,Day2022xx.part1(exInput.lineSequence()))
    }

    @Test
    fun part1Test() {
        assertEquals(0,useLines(2022,4) { Day2022xx.part1(it) })
    }


    @Test
    fun part2ExTest() {
        assertEquals(0,Day2022xx.part2(exInput.lineSequence()))
    }

    @Test
    fun part2Test() {
        assertEquals(0,useLines(2022,4) { Day2022xx.part2(it) })
    }
}