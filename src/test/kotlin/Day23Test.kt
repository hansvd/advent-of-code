import Day23.part1
import Day23.part2
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day23Test {

    val testInput =
        """#############
#...........#
###B#C#B#D###
  #A#D#C#A#
  #########"""

    @Test
    fun exampleInputTestPart1() {
        assertEquals(12521, File("input/input23example.txt").useLines { part1(it) })
    }

    @Test
    fun inputTestPart1() {
        assertEquals(0, File("input/input23.txt").useLines { part1(it) })
    }


    @Test
    fun exampleInputTestPart2() {
        assertEquals(0, part2(testInput.lineSequence()))
    }

    @Test
    fun inputTestPart2() {
        assertEquals(0, File("input/input23.txt").useLines { part2(it) })
    }


}