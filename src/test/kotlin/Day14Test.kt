import Day14.part1
import Day14.part2
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

    @Test
    fun day14aBaseTest() {
        assertEquals("NNCB", part1(testInput.lineSequence(),0).toString())
        assertEquals("NCNBCHB", part1(testInput.lineSequence(),1).toString())
        assertEquals("NBCCNBBBCBHCB", part1(testInput.lineSequence(),2).toString())
        assertEquals("NBBBCNCCNBBNBNBBCHBHHBCHB", part1(testInput.lineSequence(),3).toString())
        assertEquals("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB", part1(testInput.lineSequence(),4).toString())
    }

    @Test
    fun day14aInputTest() {
        assertEquals(1588, part1(testInput.lineSequence()).result())
    }

    @Test
    fun day14aTest() {
        assertEquals(2797, File("input/input14.txt").useLines { part1(it) }.result())
    }

    @Test
    fun day14bBaseTest() {
        assertEquals(0, part2("00000".lineSequence()))
    }

    @Test
    fun day14bInputTest() {
        assertEquals(0, part2(testInput.lineSequence()))
    }

    @Test
    fun day14bTest() {
        assertEquals(0, File("input/input14.txt").useLines { part2(it) })
    }


}