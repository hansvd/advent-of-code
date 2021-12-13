import Day13.part1
import Day13.part2
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day13Test {

    val testInput = """6,10
0,14
9,10
0,3
10,4
4,11
6,0
6,12
4,1
0,13
10,12
3,4
3,0
8,4
1,10
2,14
8,10
9,0

fold along y=7
fold along x=5"""

    @Test
    fun day13aBaseTest() {
        assertEquals(0, part1("".lines()))
    }

    @Test
    fun day13aInputTest() {
        assertEquals(17, part1(testInput.lines(),1))
        assertEquals(16, part1(testInput.lines(),2))
    }

    @Test
    fun day13aTest() {
        assertEquals(618, File("input/input13.txt").useLines { part1(it.toList()) })
    }



    @Test
    fun day13bBaseTest() {
        assertEquals(0, part2("00000".lines()))
    }

    @Test
    fun day13bInputTest() {
        assertEquals(0, part2(testInput.lines()))
    }

    @Test
    fun day13bTest() {
        assertEquals(0, File("input/input13.txt").useLines { part2(it.toList()) })
    }


}