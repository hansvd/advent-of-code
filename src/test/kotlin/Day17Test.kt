import Day17.Point
import Day17.Velocity
import Day17.go
import Day17.part1
import Day17.part2
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class Day17Test {

    @Test
    fun day17aBaseTest() {
        assertEquals(Point(28, -7), go(20..30, -10..-5, Velocity(7,2)))
        assertEquals(Point(21, -9), go(20..30, -10..-5, Velocity(6,3)))
        assertEquals(Point(30, -6), go(20..30, -10..-5, Velocity(9,0)))
        assertNull(go(20..30, -10..-5, Velocity(17,-4)))
    }

    @Test
    fun day17aInputTest() {
        assertEquals(45, part1(20..30, -10..-5))
    }

    @Test
    fun day17aTest() {
        assertEquals(6786, part1(155..182, -117..-67))
    }

    @Test
    fun day17bInputTest() {
        assertEquals(112, part2(20..30, -10..-5))
    }

    @Test
    fun day17bTest() {
        assertEquals(2313, part2(155..182, -117..-67))
    }
}