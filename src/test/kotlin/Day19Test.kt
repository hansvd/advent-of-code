import Day19.Coordinates
import Day19.parseInput
import Day19.part1
import Day19.part2
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day19Test {

    val testInput = """"""

    @Test fun sameOrientationTest() {
        assertTrue(Coordinates(-1,-1,1).samePosition(Coordinates(1,-1,1)))
        assertFalse(Coordinates(-1,-1,1).samePosition(Coordinates(1,-1,2)))

    }

    @Test
    fun exampleInputTest1() {
        val input = File("input/input19sameStates.txt").useLines { parseInput(it.toList())}
        assertEquals(5, input.size )
        assertTrue(input.all { it.bacons.size == 6})

        assertTrue(input[0].bacons.mapIndexed() { i, bacon ->
            input.all { s ->
                    println("match $bacon == ${s.bacons[i]}")
                    bacon.match(s.bacons[i]) } }.all { it })
    }

//    @Test fun matchSameBaconDifferentScannerTest() {
//        assertTrue(Coordinates(-618,-824,-621).delta(Coordinates(686,422,578),arrayOf(0,1,2)).samePosition(Coordinates(-537,-823,-458).delta(Coordinates(605,423,415),arrayOf(0,2,1))))
//
//    }

    @Test
    fun exampleInputTestPart1() {
        assertEquals(5, File("input/input19example.txt").useLines { parseInput(it.toList()).size })
    }

    @Test
    fun inputTestPart1() {
        assertEquals(0, File("input/input19.txt").useLines { part1(it) })
    }


    @Test
    fun exampleInputTestPart2() {
        assertEquals(0, part2(testInput.lineSequence()))
    }

    @Test
    fun inputTestPart2() {
        assertEquals(0, File("input/input19.txt").useLines { part2(it) })
    }


}