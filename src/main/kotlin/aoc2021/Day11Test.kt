package aoc2021

import aoc2021.Day11.part1
import aoc2021.Day11.part2
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day11Test {

    val testInput0 = "11111\n" +
            "19991\n" +
            "19191\n" +
            "19991\n" +
            "11111"
    
    val testInput = """5483143223
2745854711
5264556173
6141336146
6357385478
4167524645
2176841721
6882881134
4846848554
5283751526"""
    
    @Test
    fun day11aBaseTest() {
        assertEquals(0, part1("".lineSequence()))
    }

    @Test
    fun day11aInputTest0() {
        assertEquals(9, part1(testInput0.lineSequence(),1))
        assertEquals(9, part1(testInput0.lineSequence(),2))
    }
    
    @Test
    fun day11aInputTest() {
        assertEquals(0, part1(testInput.lineSequence(),1))
        assertEquals(35, part1(testInput.lineSequence(),2))
        assertEquals(80, part1(testInput.lineSequence(),3))
        assertEquals(1656, part1(testInput.lineSequence()))
    }

    @Test
    fun day11aTest() {
        assertEquals(1594, File("src/main/kotlin/aoc2021/input11.txt").useLines { part1(it) })
    }

    @Test
    fun day11bBaseTest() {
        assertEquals(10, part2("00000".lineSequence()))
    }

    @Test
    fun day11bInputTest() {
        assertEquals(195, part2(testInput.lineSequence()))
    }

    @Test
    fun day11bTest() {
        assertEquals(437, File("src/main/kotlin/aoc2021/input11.txt").useLines { part2(it) })
    }


}