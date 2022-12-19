package aoc2022.day17

import org.junit.jupiter.api.Test
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
        assertEquals(1514285714288L, Day202217.part2(exInput))
    }

    @Test
    fun part2Test() {
        assertEquals(1570434782634L, useText(2022,17) { Day202217.part2(it) })
    }
}