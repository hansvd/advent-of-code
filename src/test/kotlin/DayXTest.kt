import AdventX.part1
import AdventX.part2
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class DayXTest {

    val testInput = """3,4,3,1,2"""

    @Test()
    fun adventXaBaseTest() {
        assertEquals(0, part1("".lineSequence()))
    }

    @Test()
    fun adventXaInputTest() {
        assertEquals(0, part1(testInput.lineSequence()))
    }

    @Test
    fun adventXaTest() {
        assertEquals(0, File("input/inputX.txt").useLines { part1(it) })
    }

    @Test()
    fun adventXbBaseTest() {
        assertEquals(0, part2("00000".lineSequence()))
    }

    @Test()
    fun adventXbInputTest() {
        assertEquals(0, part2(testInput.lineSequence()))
    }

    @Test
    fun adventXbTest() {
        assertEquals(0, File("input/inputX.txt").useLines { part2(it) })
    }


}