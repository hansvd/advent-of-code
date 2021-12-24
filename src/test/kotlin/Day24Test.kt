import Day24.calculate
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day24Test {

    val testInput = """"""

    @Test
    fun calcTestPart1() {
        assertEquals(-3, calculate("inp x\nmul x -1".lineSequence(),"3").x.value)
        assertEquals(6, calculate("inp x\nmul x 2".lineSequence(),"3").x.value)
        assertEquals(12, calculate("inp x\ninp y\nmul x y".lineSequence(),"34").x.value)
        assertEquals(2, calculate("inp x\ninp y\ndiv x y".lineSequence(),"83").x.value)
        assertEquals(1, calculate("inp x\ninp y\nmod x y".lineSequence(),"73").x.value)
        assertEquals(0, calculate("inp x\ninp y\neql x y".lineSequence(),"73").x.value)
        assertEquals(1, calculate("inp x\ninp y\neql x y".lineSequence(),"77").x.value)
        assertEquals(0, File("input/input24example.txt").useLines { calculate(it,"4").z.value })
        assertEquals(1, File("input/input24example.txt").useLines { calculate(it,"5").z.value })
    }

    // manual analysing of input. Found the following dependencies
    // Inp4 = Inp3
    // Inp6 = Inp5 + 4
    // Inp8 = Inp7 + 3
    // Inp11 = Inp10 + 8
    // Inp12 = Inp9 - 6
    // Inp13 = Inp2 - 7
    // Inp14 = Inp1 - 3
    @Test
    fun inputTestPart1() {
        assertEquals(0, File("input/input24.txt").useLines { calculate(it,"99995969919326").z.value })
    }
    @Test
    fun inputTestPart2() {
        assertEquals(0, File("input/input24.txt").useLines { calculate(it,"48111514719111").z.value })
    }

}