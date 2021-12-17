import DayX.part1
import DayX.part2
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class DayXTest {

    val testInput = """"""

    @Test
    fun exampleInputTestPart1() {
        assertEquals(0, part1(testInput.lineSequence()))
    }

    @Test
    fun inputTestPart1() {
        assertEquals(0, File("input/inputX.txt").useLines { part1(it) })
    }


    @Test
    fun exampleInputTestPart2() {
        assertEquals(0, part2(testInput.lineSequence()))
    }

    @Test
    fun inputTestPart2() {
        assertEquals(0, File("input/inputX.txt").useLines { part2(it) })
    }


}