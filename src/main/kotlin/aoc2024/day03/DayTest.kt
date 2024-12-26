package aoc2024.day03

import org.junit.jupiter.api.Test
import shared.Input.useLines
import shared.Input.useText
import kotlin.test.assertEquals

class DayTest {

    private val year = 2024
    private val day = 3

    private val input = """xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"""
    private val input2 = """xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"""
    @Test
    fun exampleTest() {
        assertEquals(161,Day.part1(input.lines()))
    }
    @Test
    fun part1Test() {
        assertEquals(170807108,useLines(year,day) { Day.part1(it.toList()) })
    }

    @Test
    fun exampleTest2() {
        assertEquals(48,Day.part2(input2))
    }
    @Test
    fun part2Test() {
        assertEquals(74838033,useText(year,day) { Day.part2(it) })
    }
}