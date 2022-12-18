package aoc2022.day16

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202216Test {

    val exInput = """Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
Valve BB has flow rate=13; tunnels lead to valves CC, AA
Valve CC has flow rate=2; tunnels lead to valves DD, BB
Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
Valve EE has flow rate=3; tunnels lead to valves FF, DD
Valve FF has flow rate=0; tunnels lead to valves EE, GG
Valve GG has flow rate=0; tunnels lead to valves FF, HH
Valve HH has flow rate=22; tunnel leads to valve GG
Valve II has flow rate=0; tunnels lead to valves AA, JJ
Valve JJ has flow rate=21; tunnel leads to valve II"""

    @Test
    fun part1ExTest() {
        assertEquals(1651, Day202216.part1(exInput.lineSequence()))
    }

    @Test
    fun part1Test() {
        assertEquals(1474,useLines(2022,16) { Day202216.part1(it) })
    }


    @Test
    fun part2ExTest() {
        assertEquals(0, Day202216.part2(exInput.lineSequence()))
    }

    @Test
    fun part2Test() {
        assertEquals(0,useLines(2022,16) { Day202216.part2(it) })
    }
}