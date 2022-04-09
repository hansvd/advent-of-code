package aoc2021
import aoc2021.Day25.parseInput
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day25Test {

    val testInput = """"""

    @Test
    fun exampleInputExample1Test() {
        var seeBottom = parseInput(File("input/aoc2021/input25example1.txt").readLines())
        seeBottom = seeBottom.step()
        assertEquals("..vv>..\n.......\n>......\nv.....>\n>......\n.......\n....v..", seeBottom.toString())
        seeBottom = seeBottom.step().step().step()
        assertEquals(">......\n..v....\n..>.v..\n.>.v...\n...>...\n.......\nv......",seeBottom.toString())
    }

    @Test
    fun exampleInput2Test() {
        assertEquals(58, parseInput(File("input/aoc2021/input25example2.txt").readLines()).maxSteps())
    }
    @Test
    fun part1Test() {
        assertEquals(378, parseInput(File("input/aoc2021/input25.txt").readLines()).maxSteps())
    }

}