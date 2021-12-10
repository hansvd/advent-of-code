import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day2Test {

    @Test
    fun testAdvant2() {
        assertEquals(150, advent2("forward 5\ndown 5\nforward 8\nup 3\ndown 8\nforward 2".lineSequence()))
    }
    @Test
    fun testAdvant2b() {
        assertEquals(900, advent2b("forward 5\ndown 5\nforward 8\nup 3\ndown 8\nforward 2".lineSequence()))
    }
    @Test
    fun testAdvant2b1() {
        assertEquals(0, advent2b("forward 5".lineSequence()))
        assertEquals(0, advent2b("forward 5\ndown 5".lineSequence()))
        assertEquals(40*13, advent2b("forward 5\ndown 5\nforward 8".lineSequence()))
        assertEquals(40*13, advent2b("forward 5\ndown 5\nforward 8\nup 3".lineSequence()))
    }
}