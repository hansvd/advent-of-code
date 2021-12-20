import Day20.part1
import Day20.part2
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day20Test {


    @Test
    fun exampleInputTestPart1() {
        assertEquals(0,  File("input/input20example.txt").useLines { part1(it) })
    }

    @Test
    fun inputTestPart1() {
        assertEquals(0, File("input/input20.txt").useLines { part1(it) })
    }




    @Test
    fun inputTestPart2() {
        assertEquals(0,File("input/input20example.txt").useLines { part2(it) })
    }


}