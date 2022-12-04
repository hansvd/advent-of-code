package aoc2022.day02

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202204Test {

    val testInput="""2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8"""

    @Test
    fun part1ExTest() {
        assertEquals(2,Day202204.part1(testInput.lineSequence()) )
    }
    @Test
    fun part1Test() {
        assertEquals(562,useLines(2022,4) { Day202204.part1(it) })
    }
    @Test
    fun part2Test() {
        assertEquals(0,useLines(2022,4) { Day2022xx.part2(it) })
    }

}