import advent5.advent5a
import advent5.advent5b
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Advent5Test {

    val testInput = """"""

    @Test()
    fun advent5aBaseTest() {
        assertEquals(0, advent5a("00000".lineSequence()))
    }

    @Test()
    fun advent5aInputTest() {
        assertEquals(0, advent5a(testInput.lineSequence()))
    }

    @Test
    fun advent5aTest() {
        assertEquals(0, File("input/input5.txt").useLines { advent5a(it) })
    }

    @Test()
    fun advent5bBaseTest() {
        assertEquals(0, advent5b("00000".lineSequence()))
    }

    @Test()
    fun advent5bInputTest() {
        assertEquals(0, advent5b(testInput.lineSequence()))
    }

    @Test
    fun advent5bTest() {
        assertEquals(0, File("input/input5.txt").useLines { advent5b(it) })
    }


}