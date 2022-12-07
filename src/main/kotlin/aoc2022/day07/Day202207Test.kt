package aoc2022.day07

import org.junit.jupiter.api.Test
import shared.Input.useText
import kotlin.test.assertEquals

class Day202207Test {

    @Test
    fun part1ExTest() {
        assertEquals(95437,useText(2022,7, "ex") { Day202207.part1(it)})
    }

    @Test
    fun part1Test() {
        assertEquals(2031851,useText(2022,7) { Day202207.part1(it) })
    }


    @Test
    fun part2ExTest() {
        assertEquals(24933642,useText(2022,7, "ex") { Day202207.part2(it)})
    }

    @Test
    fun part2Test() {
        assertEquals(2568781,useText(2022,7) { Day202207.part2(it) })
    }
}