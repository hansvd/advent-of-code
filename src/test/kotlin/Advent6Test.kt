import Advent6.advent6a
import Advent6.advent6b
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Advent6Test {

    val testInput = """3,4,3,1,2"""

    @Test()
    fun advent6aBaseTest() {
        assertEquals(0, advent6a(""))
        assertEquals(2, advent6a("0",1))
    }

    @Test()
    fun advent6aInputTest() {
        assertEquals(5, advent6a(testInput,0))
        assertEquals(5, advent6a(testInput,1))
        assertEquals(6, advent6a(testInput,2))
        assertEquals(7, advent6a(testInput,3))
        assertEquals(9, advent6a(testInput,4))
        assertEquals(26, advent6a(testInput,18))
        assertEquals(5934, advent6a(testInput,80))
    }

    @Test
    fun advent6aTest() {
        assertEquals(0, advent6a( File("input/inputX.txt").readText()) )
    }

    @Test()
    fun advent6bBaseTest() {
        assertEquals(0, advent6b("00000".lineSequence()))
    }

    @Test()
    fun advent6bInputTest() {
        assertEquals(0, advent6b(testInput.lineSequence()))
    }

    @Test
    fun advent6bTest() {
        assertEquals(0, File("input/inputX.txt").useLines { advent6b(it) })
    }


}