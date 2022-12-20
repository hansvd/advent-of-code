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
    fun part2Ex1Test() {
        assertEquals(6, Day202218.part2("1,1,1".lineSequence()))
        assertEquals(12, Day202218.part2("1,1,1\n3,1,1".lineSequence()))
        assertEquals(12, Day202218.part2("1,1,1\n4,1,1".lineSequence()))

        assertEquals(12, Day202218.part2("1,1,1\n1,3,1".lineSequence()))
        assertEquals(12, Day202218.part2("1,1,1\n1,4,1".lineSequence()))

        assertEquals(12, Day202218.part2("1,1,1\n1,1,3".lineSequence()))
        assertEquals(12, Day202218.part2("1,1,1\n1,1,4".lineSequence()))

        assertEquals(30, Day202218.part2("1,1,1\n2,2,1\n3,1,1\n2,0,1\n2,1,0\n2,1,2".lineSequence()))
        assertEquals(24, Day202218.part2("1,1,1\n2,2,1\n3,1,1\n2,0,1".lineSequence()))
    }

    @Test
    fun part2ExTest() {
        assertEquals(58, Day202218.part2(exInput.lineSequence()))
    }

    @Test
    fun part2Test() {
        assertEquals(2074,useLines(2022,18) { Day202218.part2(it) })
    }
}