package aoc2022.day01

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202201Test {

    val input = "1000\n" +
            "2000\n" +
            "3000\n" +
            "\n" +
            "4000\n" +
            "\n" +
            "5000\n" +
            "6000\n" +
            "\n" +
            "7000\n" +
            "8000\n" +
            "9000\n" +
            "\n" +
            "10000"
    @Test
    fun exampleTest() {
        assertEquals(24000,Day202201.part1(input.lines()))
    }
    @Test
    fun part1Test() {
        assertEquals(69281,useLines(2022,1) { Day202201.part1(it.toList()) })
    }

    @Test
    fun exampleTest2() {
        assertEquals(45000,Day202201.part2(input.lines()))
    }
    @Test
    fun part2Test() {
        assertEquals(201524,useLines(2022,1) { Day202201.part2(it.toList()) })
    }
}