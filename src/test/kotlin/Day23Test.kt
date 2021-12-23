import Day23.part1
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day23Test {

    @Test
    fun exampleInputTestPart1Steps() {
        assertEquals(0, File("input/input23example1.txt").useLines { part1(it.toList().subList(35,40)) })  // last step
        assertEquals(8, File("input/input23example1.txt").useLines { part1(it.toList().subList(30,35)) })  // 1 step
        assertEquals(7000+8, File("input/input23example1.txt").useLines { part1(it.toList().subList(25,30)) })
        assertEquals(7000+8+2003, File("input/input23example1.txt").useLines { part1(it.toList().subList(20,25)) })
        assertEquals(7000+8+2003+40, File("input/input23example1.txt").useLines { part1(it.toList().subList(15,20)) })
        assertEquals(7000+8+2003+40+3030, File("input/input23example1.txt").useLines { part1(it.toList().subList(10,15)) })
        assertEquals(7000+8+2003+40+3030+400, File("input/input23example1.txt").useLines { part1(it.toList().subList(5,10)) })
        assertEquals(7000+8+2003+40+3030+400+40, File("input/input23example1.txt").useLines { part1(it.toList().subList(0,5)) })
    }

    @Test
    fun exampleInputTestPart1() {
        assertEquals(12521, File("input/input23example.txt").useLines { part1(it.toList()) })
    }

    @Test
    fun inputTestPart1() {
        assertEquals(19167, File("input/input23.txt").useLines { part1(it.toList()) })
    }

//
//    @Test
//    fun exampleInputTestPart2() {
//        assertEquals(0, part2(testInput.lineSequence()))
//    }
//
//    @Test
//    fun inputTestPart2() {
//        assertEquals(0, File("input/input23.txt").useLines { part2(it) })
//    }


}