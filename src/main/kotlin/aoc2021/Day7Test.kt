package aoc2021

import aoc2021.Advent7.advent7a
import aoc2021.Advent7.advent7b
import aoc2021.Advent7.gaussSum
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day7Test {

    val testInput = """16,1,2,0,4,2,7,1,2,14"""

    @Test
    fun advent7aBaseTest() {
        assertEquals(0, advent7a(""))
    }

    @Test
    fun advent7aInputTest() {
        assertEquals(37, advent7a(testInput))
    }

    @Test
    fun advent7aTest() {
        assertEquals(325528,  advent7a(File("src/main/kotlin/aoc2021/input7.txt").readText()) )
    }

    @Test
    fun advent7bBaseTest() {
        assertEquals(0, advent7b(""))
        assertEquals(66, gaussSum(11))
        assertEquals(0, gaussSum(0))
    }

    @Test
    fun advent7bInputTest() {
        assertEquals(168, advent7b(testInput))
    }

    @Test
    fun advent7bTest() {
        assertEquals(85015836,advent7b(File("src/main/kotlin/aoc2021/input7.txt").readText()))
    }


}