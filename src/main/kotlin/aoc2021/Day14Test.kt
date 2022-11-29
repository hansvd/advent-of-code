package aoc2021

import aoc2021.Day14.day14
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day14Test {

    val testInput = """NNCB

CH -> B
HH -> N
CB -> H
NH -> C
HB -> C
HC -> B
HN -> C
NN -> C
BH -> H
NC -> B
NB -> B
BN -> B
BB -> N
BC -> B
CC -> N
CN -> C"""

//    @Test
//    fun day14aBaseTest() {
//        assertEquals("NNCB", part1(testInput.lineSequence(),0).toString())
//        assertEquals("NCNBCHB", part1(testInput.lineSequence(),1).toString())
//        assertEquals("NBCCNBBBCBHCB", part1(testInput.lineSequence(),2).toString())
//        assertEquals("NBBBCNCCNBBNBNBBCHBHHBCHB", part1(testInput.lineSequence(),3).toString())
//        assertEquals("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB", part1(testInput.lineSequence(),4).toString())
//    }
    @Test
    fun day14aBaseTest() {
        assertEquals(1, day14(testInput.lineSequence(),0).result())
        assertEquals(1, day14(testInput.lineSequence(),1).result())
        assertEquals(5, day14(testInput.lineSequence(),2).result())
        assertEquals(7, day14(testInput.lineSequence(),3).result())
        assertEquals(18, day14(testInput.lineSequence(),4).result())
    }
    @Test
    fun day14aInputTest() {
        assertEquals(1588, day14(testInput.lineSequence()).result())
    }

    @Test
    fun day14aTest() {
        assertEquals(2797, File("src/main/kotlin/aoc2021/input14.txt").useLines { day14(it) }.result())
    }

    @Test
    fun day14bInputTest() {
        assertEquals(2188189693529, day14(testInput.lineSequence(),40).result())
    }

    @Test
    fun day14bTest() {
        assertEquals(2926813379532, File("src/main/kotlin/aoc2021/input14.txt").useLines { day14(it,40) }.result())
    }


}