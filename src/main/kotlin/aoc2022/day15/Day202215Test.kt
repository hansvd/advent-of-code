package aoc2022.day15

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202215Test {

    val exInput = """Sensor at x=2, y=18: closest beacon is at x=-2, y=15
Sensor at x=9, y=16: closest beacon is at x=10, y=16
Sensor at x=13, y=2: closest beacon is at x=15, y=3
Sensor at x=12, y=14: closest beacon is at x=10, y=16
Sensor at x=10, y=20: closest beacon is at x=10, y=16
Sensor at x=14, y=17: closest beacon is at x=10, y=16
Sensor at x=8, y=7: closest beacon is at x=2, y=10
Sensor at x=2, y=0: closest beacon is at x=2, y=10
Sensor at x=0, y=11: closest beacon is at x=2, y=10
Sensor at x=20, y=14: closest beacon is at x=25, y=17
Sensor at x=17, y=20: closest beacon is at x=21, y=22
Sensor at x=16, y=7: closest beacon is at x=15, y=3
Sensor at x=14, y=3: closest beacon is at x=15, y=3
Sensor at x=20, y=1: closest beacon is at x=15, y=3"""




    @Test
    fun part1Ex1Test() {
        assertEquals(12, Day202215.part1("Sensor at x=8, y=7: closest beacon is at x=2, y=10".lineSequence()))
    }
    @Test
    fun part1Ex2Test() {
        assertEquals(9, Day202215.part1("Sensor at x=20, y=14: closest beacon is at x=25, y=17".lineSequence()))
    }

    @Test
    fun part1ExTest() {
        assertEquals(26, Day202215.part1(exInput.lineSequence()))
    }

    @Test
    fun part1Test() {
        assertEquals(5144286,useLines(2022,15) { Day202215.part1(it,2_000_000) })
    }


    @Test
    fun part2ExTest() {
        assertEquals(0, Day202215.part2(exInput.lineSequence()))
    }

    @Test
    fun part2Test() {
        assertEquals(0,useLines(2022,15) { Day202215.part2(it) })
    }
}