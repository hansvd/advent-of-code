package aoc2022.day25

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202225Test {

    private val exInput = """1=-0-2
12111
2=0=
21
2=01
111
20012
112
1=-1=
1-12
12
1=
122"""

    private val testValues = listOf(
        1 to "1",
        2 to "2",
        3 to "1=",
        4 to "1-",
        5 to "10",
        6 to "11",
        7 to "12",
        8 to "2=",
        9 to "2-",
        10 to "20",
        15 to "1=0",
        20 to "1-0",
        2022 to "1=11-2",
        12345 to "1-0---0",
        314159265 to "1121-1110-1=0"
    )

    @Test
    fun convertTest() {
        testValues.forEach {
            assertEquals(it.first.toLong(), Day202225.snafuToDec(it.second))
        }
        testValues.forEach {
            assertEquals(it.second, Day202225.decToSnafu(it.first.toLong()))
        }
    }

    @Test
    fun part1ExTest() {
        assertEquals("2=-1=0", Day202225.part1(exInput.lineSequence()))
    }

    @Test
    fun part1Test() {
        assertEquals("2-1-110-=01-1-0-0==2", useLines(2022, 25) { Day202225.part1(it) })
    }

}