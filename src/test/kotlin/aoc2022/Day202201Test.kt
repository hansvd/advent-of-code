package aoc2022

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202201Test {

    @Test
    fun part1Test() {
        assertEquals(0,useLines(2022,1) { Day202201.part1(it)    })
    }
    @Test
    fun part2Test() {
        assertEquals(0,useLines(2022,1) { Day202201.part2(it)    })
    }
}