import DayX.part1
import DayX.part2
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day12Test {

    val testInput = """3,4,3,1,2"""

    @Test
    fun dayXaBaseTest() {
        assertEquals(0, part1("".lineSequence()))
    }

    @Test
    fun day12aInputTest() {
        assertEquals(0, part1(testInput.lineSequence()))
    }

    @Test
    fun day12aTest() {
        assertEquals(0, File("input/inputX.txt").useLines { part1(it) })
    }

    @Test
    fun day12bBaseTest() {
        assertEquals(0, part2("00000".lineSequence()))
    }

    @Test
    fun day12bInputTest() {
        assertEquals(0, part2(testInput.lineSequence()))
    }

    @Test
    fun day12bTest() {
        assertEquals(0, File("input/inputX.txt").useLines { part2(it) })
    }


}