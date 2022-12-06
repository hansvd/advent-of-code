package aoc2022.day07

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202207Test {

    val exInput = """"""

    @Test
    fun part1ExTest() {
        assertEquals(0,Day202207.part1(exInput.lineSequence()))
    }

    @Test
    fun part1Test() {
        assertEquals(0,useLines(2022,7) { Day202207.part1(it) })
    }


    @Test
    fun part2ExTest() {
        assertEquals(0,Day202207.part2(exInput.lineSequence()))
    }

    @Test
    fun part2Test() {
        assertEquals(0,useLines(2022,7) { Day202207.part2(it) })
    }
}