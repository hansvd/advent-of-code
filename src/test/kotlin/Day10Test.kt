import Day10.part1
import Day10.part2
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day10Test {

    val testInput = """3,4,3,1,2"""

    @Test()
    fun day10aBaseTest() {
        assertEquals(0, part1("".lineSequence()))
    }

    @Test()
    fun day10aInputTest() {
        assertEquals(0, part1(testInput.lineSequence()))
    }

    @Test
    fun day10aTest() {
        assertEquals(0, File("input/inputX.txt").useLines { part1(it) })
    }

    @Test()
    fun day10bBaseTest() {
        assertEquals(0, part2("00000".lineSequence()))
    }

    @Test()
    fun day10bInputTest() {
        assertEquals(0, part2(testInput.lineSequence()))
    }

    @Test
    fun day10bTest() {
        assertEquals(0, File("input/inputX.txt").useLines { part2(it) })
    }


}