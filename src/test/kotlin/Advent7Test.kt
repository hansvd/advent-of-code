import Advent7.advent7a
import Advent7.advent7b
import Advent7.cost
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Advent7Test {

    val testInput = """16,1,2,0,4,2,7,1,2,14"""

    @Test()
    fun advent7aBaseTest() {
        assertEquals(0, advent7a(""))
    }

    @Test()
    fun advent7aInputTest() {
        assertEquals(37, advent7a(testInput))
    }

    @Test
    fun advent7aTest() {
        assertEquals(325528,  advent7a(File("input/input7.txt").readText()) )
    }

    @Test()
    fun advent7bBaseTest() {
        assertEquals(0, advent7b(""))
        assertEquals(66, cost(11))
        assertEquals(0, cost(0))
    }

    @Test()
    fun advent7bInputTest() {
        assertEquals(168, advent7b(testInput))
    }

    @Test
    fun advent7bTest() {
        assertEquals(0,advent7b(File("input/input7.txt").readText()))
    }


}