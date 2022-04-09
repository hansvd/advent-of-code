package aoc2020

import org.junit.jupiter.api.Test
import shared.Input
import kotlin.test.assertEquals

class Day202002Test {
    val input = """
        1-3 a: abcde
        1-3 b: cdefg
        2-9 c: ccccccccc
    """.trimIndent()
    @Test
    fun day202002Part01ExampleTest() {
        assertEquals(2, Day202002.part01(input.lineSequence()))
    }
    @Test
    fun day202002Part01Test() {
        assertEquals(607, Input.useLines(2020,2) { Day202002.part01(it) })
    }

    @Test
    fun day202002Part02ExampleTest() {
        assertEquals(1, Day202002.part02(input.lineSequence()))
    }
    @Test
    fun day202002Part02Test() {
        assertEquals(321, Input.useLines(2020,2) { Day202002.part02(it) })
    }
}