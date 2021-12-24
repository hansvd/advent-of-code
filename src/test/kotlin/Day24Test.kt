import Day24.calculate
import Day24.part1
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

    @Test
    fun inputTestExample() {
        assertEquals(0, File("input/input24.txt").useLines { calculate(it,"13579246899999").z.value })
    }
    @Test
    fun inputTestPar1() {
        assertEquals(0, File("input/input24.txt").useLines { part1(it) })
    }

//    @Test
//    fun exampleInputTestPart2() {
//        assertEquals(0, part2(testInput.lineSequence()))
//    }
//
//    @Test
//    fun inputTestPart2() {
//        assertEquals(0, File("input/input24.txt").useLines { part2(it) })
//    }


}