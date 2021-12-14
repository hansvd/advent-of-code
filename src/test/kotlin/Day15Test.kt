import Day15.part1
import Day15.part2
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day15Test {

    val testInput = """"""

    @Test
    fun day15aBaseTest() {
        assertEquals(0, part1("".lineSequence()))
    }

    @Test
    fun day15aInputTest() {
        assertEquals(0, part1(testInput.lineSequence()))
    }

    @Test
    fun day15aTest() {
        assertEquals(0, File("input/input15.txt").useLines { part1(it) })
    }

    @Test
    fun day15bBaseTest() {
        assertEquals(0, part2("00000".lineSequence()))
    }

    @Test
    fun day15bInputTest() {
        assertEquals(0, part2(testInput.lineSequence()))
    }

    @Test
    fun day15bTest() {
        assertEquals(0, File("input/input15.txt").useLines { part2(it) })
    }


}