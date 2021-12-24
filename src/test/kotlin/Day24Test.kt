import Day24.calculateZ
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day24Test {

    val testInput = """"""

    @Test
    fun calcTestPart1() {
        assertEquals(-3, calculateZ("inp x\nmul x -1".lineSequence(),"3").x.value)
        assertEquals(6, calculateZ("inp x\nmul x 2".lineSequence(),"3").x.value)
        assertEquals(12, calculateZ("inp x\ninp y\nmul x y".lineSequence(),"34").x.value)
        assertEquals(2, calculateZ("inp x\ninp y\ndiv x y".lineSequence(),"83").x.value)
        assertEquals(1, calculateZ("inp x\ninp y\nmod x y".lineSequence(),"73").x.value)
        assertEquals(0, calculateZ("inp x\ninp y\neql x y".lineSequence(),"73").x.value)
        assertEquals(1, calculateZ("inp x\ninp y\neql x y".lineSequence(),"77").x.value)
        assertEquals(0, File("input/input24example.txt").useLines { calculateZ(it,"4").z.value })
        assertEquals(1, File("input/input24example.txt").useLines { calculateZ(it,"5").z.value })
    }

//    @Test
//    fun inputTestPart1() {
//        assertEquals(0, File("input/input24.txt").useLines { part1(it) })
//    }


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