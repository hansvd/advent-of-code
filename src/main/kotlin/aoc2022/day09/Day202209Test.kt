package aoc2022.day09

import aoc2022.day09.Day202209.Point
import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202209Test {

    val exInput = """R 4
U 4
L 3
D 1
R 4
D 1
L 5
R 2"""

    @Test
    fun moveTest() {
        val grid = Day202209.Grid()
        grid.step('R')
        assertEquals(Point(0, 0), grid.tail)
        assertEquals(Point(1, 0), grid.head)

        grid.step('R')
        assertEquals(Point(1, 0), grid.tail)
        assertEquals(Point(2, 0), grid.head)

        repeat(2) { grid.step('R'); }
        assertEquals(Point(3, 0), grid.tail)
        assertEquals(Point(4, 0), grid.head)


        grid.step('U')
        assertEquals(Point(3, 0), grid.tail)
        assertEquals(Point(4, 1), grid.head)

        grid.step('U')
        assertEquals(Point(4, 1), grid.tail)
        assertEquals(Point(4, 2), grid.head)
    }

    @Test
    fun part1ExTest() {
         assertEquals(13, Day202209.part1(exInput.lineSequence()))
    }

    @Test
    fun part1Test() {
        assertEquals(6037,useLines(2022,9) { Day202209.part1(it) })
    }


    @Test
    fun part2ExTest() {
        assertEquals(1, Day202209.part2(exInput.lineSequence()))
    }

    @Test
    fun part2Ex2Test() {
        val input = """R 5
U 8
L 8
D 3
R 17
D 10
L 25
U 20"""
        assertEquals(36, Day202209.part2(input.lineSequence()))

    }

    @Test
    fun part2Test() {
        assertEquals(2485,useLines(2022,9) { Day202209.part2(it) })
    }
}