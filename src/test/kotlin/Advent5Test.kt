import advent5.advent5a
import advent5.advent5b
import advent5.parseLine
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Advent5Test {

    val testInput = """0,9 -> 5,9
8,0 -> 0,8
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
6,4 -> 2,0
0,9 -> 2,9
3,4 -> 1,4
0,0 -> 8,8
5,5 -> 8,2"""

    @Test()
    fun advent5aBaseTest() {
        assertEquals(advent5.Line(0, 9, 5, 9), parseLine("0,9 -> 5,9"))
        assertEquals(0, advent5a(testInput.lineSequence().take(1)))
        assertEquals(0, advent5a(testInput.lineSequence().take(2)))
        assertEquals(6, advent5a("0,9->5,9\n0,9->5,9".lineSequence()))
        assertEquals(5, advent5a("0,9->5,9\n1,9->6,9".lineSequence()))
        assertEquals(6, advent5a("9,0->9,5\n9,0->9,5".lineSequence()))
        assertEquals(5, advent5a("9,0->9,5\n9,1->9,6".lineSequence()))
        assertEquals(6, advent5a("9,5->9,0\n9,5->9,0".lineSequence()))
        assertEquals(1, advent5a("5,0->5,9\n0,5->9,5".lineSequence()))
        assertEquals(19, advent5a("5,0->5,9\n0,5->9,5\n5,0->5,9\n0,5->9,5".lineSequence()))
        assertEquals(1, advent5a("5,9->5,0\n9,5->0,5".lineSequence()))
    }

    @Test()
    fun advent5aInputTest() {
        assertEquals(5, advent5a(testInput.lineSequence()))
    }

    @Test
    fun advent5aTest() {
        assertEquals(7644, File("input/input5.txt").useLines { advent5a(it) })
    }

    @Test()
    fun advent5bBaseTest() {
        assertEquals(0, advent5b(testInput.lineSequence().take(1)))
        assertEquals(0, advent5b(testInput.lineSequence().take(2)))
        assertEquals(1, advent5b(testInput.lineSequence().take(3)))
        assertEquals(5, advent5b(testInput.lineSequence().take(6)))
    }

    @Test()
    fun advent5bInputTest() {
        assertEquals(12, advent5b(testInput.lineSequence()))
    }

    @Test
    fun advent5bTest() {
        assertEquals(18627, File("input/input5.txt").useLines { advent5b(it) })
    }


}