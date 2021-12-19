import Day19.Coordinates
import Day19.parseInput
import Day19.setOffsets
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

        assertTrue(input[0].bacons.mapIndexed() { i, bacon ->
            input.all { s ->
                println("match $bacon == ${s.bacons[i]}")
                bacon.match(s.bacons[i])
            }
        }.all { it })
    }


    @Test
    fun exampleInputTest1() {
        val input = File("input/input19example.txt").useLines { parseInput(it.toList()) }
        assertEquals(5, input.size)

        input[0].setOffsets(input[1])
        assertEquals(Coordinates(68, -1246, -43), input[1].coordinates)

        input[1].setOffsets(input[4])
        assertEquals(Coordinates(-20, -1359, 1061), input[4].coordinates)
    }

    @Test
    fun exampleInputTest2() {
        val input = File("input/input19example.txt").useLines { parseInput(it.toList()) }
        assertEquals(5, input.size)

        setOffsets(input)

        assertEquals(Coordinates(68, -1246, -43), input[1].coordinates)
        assertEquals(Coordinates(1105, -1205, 1229), input[2].coordinates)
        assertEquals(Coordinates(-92, -2380, -20), input[3].coordinates)
        assertEquals(Coordinates(-20, -1133, 1061), input[4].coordinates)

        assertEquals(79,input[0].bacons.size)
    }

    @Test
    fun inputTestPart1() {
        val input = File("input/input19.txt").useLines { parseInput(it.toList()) }
        setOffsets(input)
        assertEquals(390, input[0].bacons.size)
    }




}