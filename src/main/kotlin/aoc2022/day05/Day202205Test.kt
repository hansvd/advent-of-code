package aoc2022.day05

import org.junit.jupiter.api.Test
import shared.Input.getLines
import kotlin.test.assertEquals

class Day202205Test {


    @Test
    fun part1ExTest() {
        assertEquals("CMZ", getLines(2022,5,"ex") { Day202205.part1(it) })
    }
    @Test
    fun part1Test() {
        assertEquals("DHBJQJCCW",getLines(2022,5) { Day202205.part1(it) })
    }

    @Test
    fun part2ExTest() {
        assertEquals("MCD", getLines(2022,5, "ex") { Day202205.part2(it) })
    }
    @Test
    fun part2Test() {
        assertEquals("WJVRLSJJT",getLines(2022,5) { Day202205.part2(it) })
    }
}