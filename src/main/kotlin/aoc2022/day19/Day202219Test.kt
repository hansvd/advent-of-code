package aoc2022.day19

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202219Test {


    @Test
    fun part1Ex2Test() {
        assertEquals(9, useLines(2022, 19, "ex2") { Day202219.part1(it) })
    }

    @Test
    fun part1ExTest() {
        assertEquals(33, useLines(2022, 19, "ex") { Day202219.part1(it) })
    }

    @Test
    fun part1Test() {
        assertEquals(1404, useLines(2022, 19) { Day202219.part1(it) })
    }

    @Test
    fun part2Ex2Test() {
        assertEquals(56, useLines(2022, 19, "ex2") { Day202219.part2(it) })
    }

    @Test
    fun part2ExTest() {
        assertEquals(62*56, useLines(2022, 19, "ex") { Day202219.part2(it) })
    }


    @Test
    fun part2Test() {
        assertEquals(21*7*40, useLines(2022, 19) { Day202219.part2(it) })
    }
}