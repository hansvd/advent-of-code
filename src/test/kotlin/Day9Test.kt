import Advent9.advent9a
import Advent9.advent9b
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day9Test {

    val testInput = """2199943210
3987894921
9856789892
8767896789
9899965678"""

    @Test
    fun advent9aBaseTest() {
        assertEquals(0, advent9a("".lineSequence()))
        assertEquals(1, advent9a("0".lineSequence()))
    }

    @Test
    fun advent9aInputTest() {
        assertEquals(15, advent9a(testInput.lineSequence()))
    }

    @Test
    fun advent9aTest() {
        assertEquals(458, File("input/input9.txt").useLines { advent9a(it) })
    }

    @Test
    fun advent9bInputTest() {
        assertEquals(1134, advent9b(testInput.lineSequence()))
    }

    @Test
    fun advent9bTest() {
        assertEquals(1391940, File("input/input9.txt").useLines { advent9b(it) })
    }


}