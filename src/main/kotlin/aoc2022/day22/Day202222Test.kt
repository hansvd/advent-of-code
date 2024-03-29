package aoc2022.day22

import org.junit.jupiter.api.Test
import shared.Input.getLines
import kotlin.test.assertEquals

class Day202222Test {

    val exInput = """
        ...#
        .#..
        #...
        ....
...#.......#
........#...
..#....#....
..........#.
        ...#....
        .....#..
        .#......
        ......#.

10R5L5R10L4R5L5""".trimStart('\n')

    @Test
    fun part1ExTest() {
        assertEquals(6032, Day202222.part1(exInput.lines()))
    }

    @Test
    fun part1Test() {
        assertEquals(189140, getLines(2022,22) { Day202222.part1(it) })
    }


    @Test
    fun part2ExTest() {
        assertEquals(5031, Day202222.part2(exInput.lines(),true))
    }


//    @Test
//    fun part2ExTest1() {
//        Day202222.parseInput(exInput2.lines()).toCube(false).print()
//        assertEquals(5031, Day202222.part2(exInput2.lines(),false))
//    }

    //31571 < x < 139369
    //            115063
    @Test
    fun part2Test() {
        assertEquals(115063, getLines(2022,22) { Day202222.part2(it,false) })
    }
}