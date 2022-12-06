package aoc2022.day06

import org.junit.jupiter.api.Test
import shared.Input.useText
import kotlin.test.assertEquals

class Day202206Test {

    @Test
    fun part1ExTest() {
        assertEquals(7,Day202206.part1("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
        assertEquals(6,Day202206.part1("nppdvjthqldpwncqszvftbrmjlhg"))
        assertEquals(10,Day202206.part1("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))
        assertEquals(11,Day202206.part1("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))

    }

    @Test
    fun part1Test() {
        assertEquals(1198,useText(2022,6) { Day202206.part1(it) })
    }

    @Test
    fun part2ExTest() {
        assertEquals(19,Day202206.part2("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
        assertEquals(23,Day202206.part2("bvwbjplbgvbhsrlpgdmjqwftvncz"))
        assertEquals(23,Day202206.part2("nppdvjthqldpwncqszvftbrmjlhg"))
        assertEquals(29,Day202206.part2("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))
        assertEquals(26,Day202206.part2("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))

    }

    @Test
    fun part2Test() {
        assertEquals(3120,useText(2022,6) { Day202206.part2(it) })
    }
}