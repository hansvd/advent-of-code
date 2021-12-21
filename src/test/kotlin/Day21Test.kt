import Day21.Player
import Day21.part1
import Day21.part2
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day21Test {

    val testInput = """"""

    @Test
    fun exampleInputTestPart1() {
        assertEquals(739785, part1(Player(4), Player(8)))
    }

    @Test
    fun inputTestPart1() {
        assertEquals(742257,  part1(Player(10), Player(3)))
    }


    @Test
    fun exampleInputTestPart2() {
        assertEquals(444356092776315, part2(Player(4), Player(8)))
    }

    @Test
    fun inputTestPart2() {
        assertEquals(93726416205179, part2(Player(10), Player(3)))
    }


}