package aoc2021

import aoc2021.Day12.Companion.part1
import aoc2021.Day12.Companion.part2
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day12Test {

    val testInput = """start-A
start-b
A-c
A-b
b-d
A-end
b-end"""

    val testInput2="""dc-end
HN-start
start-kj
dc-start
dc-HN
LN-dc
HN-end
kj-sa
kj-HN
kj-dc"""

    val testInput3 = """fs-end
he-DX
fs-he
start-DX
pj-DX
end-zg
zg-sl
zg-pj
pj-he
RW-he
fs-DX
pj-RW
zg-RW
start-pj
he-WI
zg-he
pj-fs
start-RW"""

    @Test
    fun day12aBaseTest() {
        assertEquals(0, part1("".lineSequence()))
    }

    @Test
    fun day12aInputTest() {
        assertEquals(10, part1(testInput.lineSequence()))
    }
    @Test
    fun day12aInputTest2() {
        assertEquals(19, part1(testInput2.lineSequence()))
    }
    @Test
    fun day12aInputTest3() {
        assertEquals(226, part1(testInput3.lineSequence()))
    }

    @Test
    fun day12aTest() {
        assertEquals(3497, File("input/aoc2021/input12.txt").useLines { part1(it) })
    }

    @Test
    fun day12bBaseTest() {
        assertEquals(0, part2("00000".lineSequence()))
    }

    @Test
    fun day12bInputTest() {
        assertEquals(36, part2(testInput.lineSequence()))
    }
    @Test
    fun day12bInputTest2() {
        assertEquals(103, part2(testInput2.lineSequence()))
    }
    @Test
    fun day12bInputTest3() {
        assertEquals(3509, part2(testInput3.lineSequence()))
    }
    @Test
    fun day12bTest() {
        assertEquals(93686, File("input/aoc2021/input12.txt").useLines { part2(it) })
    }


}