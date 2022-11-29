package aoc2021

import aoc2021.Advent6.advent6
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day6Test {

    private val testInput = """3,4,3,1,2"""

    @Test
    fun advent6BaseTest() {
        assertEquals(0, advent6(""))
        assertEquals(2, advent6("0",1))
    }

    @Test
    fun advent6InputTest() {
        assertEquals(5, advent6(testInput,0))
        assertEquals(5, advent6(testInput,1))
        assertEquals(6, advent6(testInput,2))
        assertEquals(7, advent6(testInput,3))
        assertEquals(9, advent6(testInput,4))
        assertEquals(26, advent6(testInput,18))
        assertEquals(5934, advent6(testInput,80))
    }

    @Test
    fun advent6Test() {
        assertEquals(372300, advent6( File("src/main/kotlin/aoc2021/input6.txt").readText()) )
    }


    @Test
    fun advent6bTest() {
        assertEquals(1675781200288, advent6( File("src/main/kotlin/aoc2021/input6.txt").readText(), 256))
    }


}