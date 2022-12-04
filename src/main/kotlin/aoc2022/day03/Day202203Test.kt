package aoc2022.day03

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202203Test {

    val testInput="""vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw"""

    @Test
    fun part1ExTest() {
        assertEquals(16,Day202203.part1("vJrwpWtwJgWrhcsFMMfFFhFp".lineSequence()))
        assertEquals(38,Day202203.part1("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL".lineSequence()))
        assertEquals(157, Day202203.part1(testInput.lineSequence()))
    }

    @Test
    fun part2ExTest() {
        assertEquals(70, Day202203.part2(testInput.lineSequence()))
    }

    @Test
    fun part1Test() {
        assertEquals(8109,useLines(2022,3) { Day202203.part1(it) })
    }

    @Test
    fun part2Test() {
        assertEquals(2738,useLines(2022,3) { Day202203.part2(it) })
    }
}