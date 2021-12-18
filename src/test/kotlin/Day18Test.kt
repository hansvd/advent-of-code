import Day18.SnailFishValue
import Day18.parseInput
import Day18.parseInputLines
import Day18.parseValue
import Day18.toValue
import DayX.part1
import DayX.part2
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class Day18Test {

    val testInput = """[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]
[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]
[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]
[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]
[7,[5,[[3,8],[1,4]]]]
[[2,[2,2]],[8,[8,1]]]
[2,9]
[1,[[[9,3],9],[[9,0],[0,7]]]]
[[[5,[7,4]],7],1]
[[[[4,2],2],6],[8,7]]"""

    @Test
    fun parseValueTest() {
        assertEquals(1.toValue(), parseValue("1"))
        assertEquals(SnailFishValue(1.toValue(), 2.toValue()), parseValue("[1,2]"))
        assertEquals(SnailFishValue(SnailFishValue(1.toValue(), 2.toValue()), 3.toValue()), parseInput("[[1,2],3]"))
        assertNotNull(parseInput("[[[1,3],[5,3]],[[1,3],[8,7]]],[[[4,9],[6,9]],[[8,2],[7,3]]]]"))
    }

    @Test
    fun exampleInputTest() {
        assertEquals("[[[[0,9],2],3],4]", parseInput("[[[[[9,8],1],2],3],4]").explode().toString())
        assertEquals("[7,[6,[5,[7,0]]]]", parseInput("[7,[6,[5,[4,[3,2]]]]]").explode().toString())
        assertEquals("[[6,[5,[7,0]]],3]", parseInput("[[6,[5,[4,[3,2]]]],1]").explode().toString())
        assertEquals("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]",
            parseInput("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]").explode().toString()
        )
        assertEquals("[[3,[2,[8,0]]],[9,[5,[7,0]]]]",
            parseInput("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]").explode().toString()
        )
        assertEquals("[[[[0,7],4],[[7,8],[0,13]]],[1,1]]",
            parseInput("[[[[0,7],4],[15,[0,13]]],[1,1]]").splitter().toString()
        )
        assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]",
            parseInput("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]").reduce().toString()
        )
    }

    @Test fun inputListTest() {
        assertEquals("[[[[1,1],[2,2]],[3,3]],[4,4]]", parseInputLines("[1,1]\n[2,2]\n[3,3]\n[4,4]".lineSequence()).toString())
        assertEquals("[[[[3,0],[5,3]],[4,4]],[5,5]]", parseInputLines("[1,1]\n[2,2]\n[3,3]\n[4,4]\n[5,5]".lineSequence()).toString())
        assertEquals("[[[[5,0],[7,4]],[5,5]],[6,6]]", parseInputLines("[1,1]\n[2,2]\n[3,3]\n[4,4]\n[5,5]\n[6,6]".lineSequence()).toString())

        assertEquals("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]", parseInput("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]").reduce().toString())
        assertEquals("[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]", parseInput("[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]").reduce().toString())


    }
    @Test
    fun exampleInputListTest() {
        assertEquals("[[[[6,6],[7,7]],[[0,7],[7,7]]],[[[5,5],[5,6]],9]]", parseInput("[[[[[6,6],[6,6]],[[6,0],[6,7]]],[[[7,7],[8,9]],[8,[8,1]]]],[2,9]]").reduce().toString())

        assertEquals("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]", parseInput("[[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]],[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]]").reduce().toString())
        assertEquals("[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]]",parseInput("[[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]],[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]]").reduce().toString())
        assertEquals("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]", parseInput("[[[[[7,7],[7,7]],[[8,7],[8,7]]],[[[7,0],[7,7]],9]],[[[[4,2],2],6],[8,7]]]").reduce().toString())
        assertEquals("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]", parseInputLines(testInput.lineSequence()).toString() )

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