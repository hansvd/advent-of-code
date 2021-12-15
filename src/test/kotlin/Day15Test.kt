import Day15.part1
import Day15.part2
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day15Test {

    val testInput = """1163751742
1381373672
2136511328
3694931569
7463417111
1319128137
1359912421
3125421639
1293138521
2311944581"""

    @Test
    fun day15aBaseTest() {
        assertEquals(0, part1("".lineSequence()))
    }

    @Test
    fun day15aInputTest() {
        assertEquals(40, part1(testInput.lineSequence()))
    }

    @Test
    fun day15aTest() {
        assertEquals(720, File("input/input15.txt").useLines { part1(it) })
    }

    @Test
    fun day15bBaseTest() {
        assertEquals(0, part2("00000".lineSequence()))
    }

    @Test
    fun day15bInputTest() {
        assertEquals(0, part2(testInput.lineSequence()))
    }

    @Test
    fun day15bTest() {
        assertEquals(0, File("input/input15.txt").useLines { part2(it) })
    }


}