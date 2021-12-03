import Advent4.advent4a
import Advent4.advent4b
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Advent4Test {

    val testInput = """"""

    @Test()
    fun advent4aBaseTest() {
        assertEquals(0, advent4a("00000".lineSequence()))
    }

    @Test()
    fun advent4aInputTest() {
        assertEquals(0, advent4a(testInput.lineSequence()))
    }

    @Test
    fun advent4aTest() {
        assertEquals(0, File("input4.txt").useLines { advent4a(it) })
    }

    @Test()
    fun advent4bBaseTest() {
        assertEquals(0, advent4b("00000".lineSequence()))
    }

    @Test()
    fun advent4bInputTest() {
        assertEquals(0, advent4b(testInput.lineSequence()))
    }

    @Test
    fun advent4bTest() {
        assertEquals(0, File("input4.txt").useLines { advent4b(it) })
    }


}