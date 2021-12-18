import Day18.SnailFishValue
import Day18.parseInput
import Day18.parseValue
import Day18.toValue
import DayX.part1
import DayX.part2
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class Day18Test {

    val testInput = """[1,2]
[[1,2],3]
[9,[8,7]]
[[1,9],[8,5]]
[[[[1,2],[3,4]],[[5,6],[7,8]]],9]
[[[9,[3,8]],[[0,9],6]],[[[3,7],[4,9]],3]]
[[[[1,3],[5,3]],[[1,3],[8,7]]],[[[4,9],[6,9]],[[8,2],[7,3]]]]"""

    @Test
    fun parseValueTest() {
        assertEquals(1.toValue(), parseValue("1"))
        assertEquals(SnailFishValue(1.toValue(), 2.toValue()), parseValue("[1,2]"))
        assertEquals(SnailFishValue(SnailFishValue(1.toValue(), 2.toValue()),3.toValue()), parseInput("[[1,2],3]"))
        assertNotNull(parseInput("[[[1,3],[5,3]],[[1,3],[8,7]]],[[[4,9],[6,9]],[[8,2],[7,3]]]]"))
    }
    @Test
    fun exampleInputTestPart1() {
        assertEquals("[[[[0,9],2],3],4]", parseInput("[[[[[9,8],1],2],3],4]").explode().toString())
        assertEquals("[7,[6,[5,[7,0]]]]", parseInput("[7,[6,[5,[4,[3,2]]]]]").explode().toString())
        assertEquals("[[6,[5,[7,0]]],3]", parseInput("[[6,[5,[4,[3,2]]]],1]").explode().toString())
        assertEquals("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]", parseInput("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]").explode().toString())
        assertEquals("[[3,[2,[8,0]]],[9,[5,[7,0]]]]", parseInput("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]").explode().toString())
        assertEquals("[[[[0,7],4],[[7,8],[0,13]]],[1,1]]", parseInput("[[[[0,7],4],[15,[0,13]]],[1,1]]").splitter().toString())
        assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", parseInput("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]").reduce().toString())
    }

    @Test
    fun inputTestPart1() {
        assertEquals(0, File("input/inputX.txt").useLines { part1(it) })
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