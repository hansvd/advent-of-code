package aoc2022.day17

import org.junit.jupiter.api.Test
import shared.Input.useLines
import shared.Input.useText
import kotlin.test.assertEquals

class Day202217Test {

    val exInput = """>>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"""


    @Test
    fun part1ExTest() {
        assertEquals(3068, Day202217.part1(exInput))
    }

    @Test
    fun part1Test() {
        assertEquals(3181, useText(2022,17) { Day202217.part1(it) })
    }


    @Test
    fun part2ExTest() {
        assertEquals(0, Day202217.part2(exInput.lineSequence()))
    }

    @Test
    fun part2Test() {
        assertEquals(0,useLines(2022,17) { Day202217.part2(it) })
    }
}