import Day21.Player
import Day21.part1
import Day21.part2
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day21Test {

    val testInput = """"""

    @Test
    fun exampleInputTestPart1() {
        assertEquals(739785, part1(listOf(Player(1,4), Player(2,8))))
    }

    @Test
    fun inputTestPart1() {
        assertEquals(742257,  part1(listOf(Player(1,10), Player(2,3))))
    }


    @Test
    fun exampleInputTestPart2() {
        assertEquals(0, part2(testInput.lineSequence()))
    }

    @Test
    fun inputTestPart2() {
        assertEquals(0, File("input/inputX.txt").useLines { part2(it) })
    }


}