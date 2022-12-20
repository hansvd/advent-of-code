package aoc2022.day18

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202218Test {

    val exInput = """2,2,2
1,2,2
3,2,2
2,1,2
2,3,2
2,2,1
2,2,3
2,2,4
2,2,6
1,2,5
3,2,5
2,1,5
2,3,5"""

    @Test
    fun part1Ex1Test() {
        assertEquals(10, Day202218.part1("1,1,1\n2,1,1".lineSequence()))
    }
    @Test
    fun part1ExTest() {
        assertEquals(64, Day202218.part1(exInput.lineSequence()))
    }

    @Test
    fun part1Test() {
        assertEquals(3522,useLines(2022,18) { Day202218.part1(it) })
    }


    @Test
    fun part2ExTest() {
        assertEquals(0, Day202218.part2(exInput.lineSequence()))
    }

    @Test
    fun part2Test() {
        assertEquals(0,useLines(2022,18) { Day202218.part2(it) })
    }
}