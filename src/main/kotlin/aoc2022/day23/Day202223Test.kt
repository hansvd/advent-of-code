package aoc2022.day23

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202223Test {

    val exInput = """....#..
..###.#
#...#.#
.#...##
#.###..
##.#.##
.#..#.."""

    @Test
    fun part1ExTest() {
        assertEquals(110, Day202223.part1(exInput.lineSequence()))
    }

    @Test
    fun part1Test() {
        assertEquals(3780,useLines(2022,23) { Day202223.part1(it) })
    }


    @Test
    fun part2ExTest() {
        assertEquals(20, Day202223.part2(exInput.lineSequence()))
    }

    @Test
    fun part2Test() {
        assertEquals(930,useLines(2022,23) { Day202223.part2(it) })
    }
}