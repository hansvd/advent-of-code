package aoc2022.day05

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202205Test {

    val exampleInput =""""""

    @Test
    fun part1ExTest() {
        assertEquals(0, Day202205.part1(exampleInput.lineSequence()))
    }
    @Test
    fun part1Test() {
        assertEquals(0,useLines(2022,4) { Day202205.part1(it) })
    }

    @Test
    fun part2ExTest() {
        assertEquals(0, Day202205.part2(exampleInput.lineSequence()))
    }
    @Test
    fun part2Test() {
        assertEquals(0,useLines(2022,4) { Day202205.part2(it) })
    }
}