import DayX.part1
import DayX.part2
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class DayXTest {

    val testInput = """"""

    @Test
    fun dayXaBaseTest() {
        assertEquals(0, part1("".lineSequence()))
    }

    @Test
    fun dayXaInputTest() {
        assertEquals(0, part1(testInput.lineSequence()))
    }

    @Test
    fun dayXaTest() {
        assertEquals(0, File("input/inputX.txt").useLines { part1(it) })
    }

    @Test
    fun dayXbBaseTest() {
        assertEquals(0, part2("00000".lineSequence()))
    }

    @Test
    fun dayXbInputTest() {
        assertEquals(0, part2(testInput.lineSequence()))
    }

    @Test
    fun dayXbTest() {
        assertEquals(0, File("input/inputX.txt").useLines { part2(it) })
    }


}