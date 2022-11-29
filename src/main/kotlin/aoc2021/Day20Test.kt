package aoc2021

import aoc2021.Day20.invoke
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day20Test {


    @Test
    fun exampleInputTestPart1() {
        assertEquals(35,  File("src/main/kotlin/aoc2021/input20example.txt").useLines { invoke(it,2) })
    }

    @Test
    fun inputTestPart1() {
        assertEquals(5179, File("src/main/kotlin/aoc2021/input20.txt").useLines { invoke(it,2) })
    }

    @Test
    fun exampleInputTestPart2() {
        assertEquals(3351,  File("src/main/kotlin/aoc2021/input20example.txt").useLines { invoke(it,50) })
    }


    @Test
    fun inputTestPart2() {
        assertEquals(16112,File("src/main/kotlin/aoc2021/input20.txt").useLines { invoke(it,50) })
    }


}