import Day19.Coordinates
import Day19.match
import Day19.maxDistance
import Day19.merge
import Day19.parseInput
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day19Test {

    val testInput = """"""

    @Test
    fun sameOrientationTest() {
        assertTrue(Coordinates(-1, -1, 1).samePosition(Coordinates(1, -1, 1)))
        assertFalse(Coordinates(-1, -1, 1).samePosition(Coordinates(1, -1, 2)))

    }

    @Test
    fun sameStateInputTest1() {
        val input = File("input/input19sameStates.txt").useLines { parseInput(it.toList()) }
        assertEquals(5, input.size)
        assertTrue(input.all { it.bacons.size == 6 })

        assertTrue(input[0].bacons.toList().mapIndexed() { i, bacon ->
            input.all { s ->
                bacon.match(s.bacons.toList()[i])
            }
        }.all { it })
    }


    @Test
    fun exampleInputTest1() {
        val input = File("input/input19example.txt").useLines { parseInput(it.toList()) }
        assertEquals(5, input.size)

        input[0].merge(input[1])
        assertEquals(Coordinates(68, -1246, -43), input[1].coordinates)

        input[1].merge(input[4])
        assertEquals(Coordinates(-20, -1359, 1061), input[4].coordinates)
    }

    @Test
    fun exampleInputTest2() {
        val input = File("input/input19example.txt").useLines { parseInput(it.toList()) }
        assertEquals(5, input.size)

        merge(input)

        assertEquals(Coordinates(68, -1246, -43), input[1].coordinates)
        assertEquals(Coordinates(1105, -1205, 1229), input[2].coordinates)
        assertEquals(Coordinates(-92, -2380, -20), input[3].coordinates)
        assertEquals(Coordinates(-20, -1133, 1061), input[4].coordinates)

        assertEquals(79,input[0].bacons.size)
    }

    @Test
    fun inputTestPart1() {
        val input = File("input/input19.txt").useLines { parseInput(it.toList()) }
        merge(input)
        assertEquals(390, input[0].bacons.size)
    }


    @Test
    fun exampleTestPart2() {
        assertEquals(3621, Coordinates(1105,-1205,1229).distance(Coordinates(-92,-2380,-20)))


        val input = File("input/input19example.txt").useLines { parseInput(it.toList()) }
        merge(input)

        assertEquals(3621, maxDistance(input))
    }

    @Test
    fun inputTestPart2() {
        val input = File("input/input19.txt").useLines { parseInput(it.toList()) }
        merge(input)

        assertEquals(13327, maxDistance(input))
    }

}