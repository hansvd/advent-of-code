package aoc2022.day10

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202210Test {

    @Test
    fun part1ExTest() {
        assertEquals(13140, useLines(2022,10, "ex") { Day202210.part1(it) })
    }

    @Test
    fun part1Test() {
        assertEquals(15220,useLines(2022,10) { Day202210.part1(it) })
    }


    @Test
    fun part2ExTest() {
        val exp = """
            ##..##..##..##..##..##..##..##..##..##..
            ###...###...###...###...###...###...###.
            ####....####....####....####....####....
            #####.....#####.....#####.....#####.....
            ######......######......######......####
            #######.......#######.......#######.....""".trimIndent()
        assertEquals(exp, useLines(2022,10, "ex") { Day202210.part2(it) })
    }

    @Test
    fun part2Test() {
        val exp = """
            ###..####.####.####.#..#.###..####..##..
            #..#.#.......#.#....#.#..#..#.#....#..#.
            #..#.###....#..###..##...###..###..#..#.
            ###..#.....#...#....#.#..#..#.#....####.
            #.#..#....#....#....#.#..#..#.#....#..#.
            #..#.#....####.####.#..#.###..#....#..#.
        """.trimIndent()
        assertEquals(exp,useLines(2022,10) { Day202210.part2(it) })
    }
}