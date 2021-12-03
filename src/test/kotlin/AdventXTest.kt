import AdventX.adventXa
import AdventX.adventXb
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class AdventXTest {

    val testInput = """"""

    @Test()
    fun adventXaBaseTest() {
        assertEquals(0, adventXa("00000".lineSequence()))
    }

    @Test()
    fun adventXaInputTest() {
        assertEquals(0, adventXa(testInput.lineSequence()))
    }

    @Test
    fun adventXaTest() {
        assertEquals(0, File("inputX.txt").useLines { adventXa(it) })
    }

    @Test()
    fun adventXbBaseTest() {
        assertEquals(0, adventXb("00000".lineSequence()))
    }

    @Test()
    fun adventXbInputTest() {
        assertEquals(0, adventXb(testInput.lineSequence()))
    }

    @Test
    fun adventXbTest() {
        assertEquals(0, File("inputX.txt").useLines { adventXb(it) })
    }


}