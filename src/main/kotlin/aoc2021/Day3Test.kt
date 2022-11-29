package aoc2021

import aoc2021.Advent3.advent3
import aoc2021.Advent3.advent3b
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day3Test {

    val testInput="""00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010"""

    @Test
    fun advent3BaseTest() {
        assertEquals(0, advent3("00000".lines()))
        assertEquals(16 * 15, advent3("10000".lines()))
        assertEquals(0, advent3("10000\n01111".lines()))
        assertEquals(198, advent3(testInput.lines()))
    }

    @Test
    fun advent3bBaseTest() {
        assertEquals(0, advent3("00000".lines()))
        assertEquals(16 * 15, advent3("10000".lines()))
        assertEquals(0, advent3("10000\n01111".lines()))
        assertEquals(230, advent3b(testInput.lines()))
    }
    @Test
    fun advent3Test() {
        assertEquals(741950, File("src/main/kotlin/aoc2021/3.txt").useLines { advent3(it.toList()) })
    }

    @Test
    fun advent3bTest() {
        assertEquals(903810, File("src/main/kotlin/aoc2021/3.txt").useLines { advent3b(it.toList()) })
    }


}