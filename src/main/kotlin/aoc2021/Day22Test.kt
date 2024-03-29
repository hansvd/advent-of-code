package aoc2021
import aoc2021.Day22.invoke
import org.junit.jupiter.api.Test
import shared.intersect
import java.io.File
import kotlin.test.assertEquals

class Day22Test {


    @Test
    fun baseTests() {

        assertEquals(IntRange(-41,9), IntRange(-41,9).intersect(-50..50))
        assertEquals(IntRange(-41,9), IntRange(-41,9).intersect(-50..50))
        assertEquals(-50..50, (-54112..39298).intersect(-50..50))
        assertEquals(-50..-50, (-50..-50).intersect(-50..50))
        assertEquals(50..50, (50..50).intersect(-50..50))
        assertEquals(-50..50, (-150..150).intersect(-50..50))


        assertEquals(27,invoke("on x=10..12,y=10..12,z=10..12".lineSequence(),-50..50))
        assertEquals(27+19,invoke("on x=10..12,y=10..12,z=10..12\non x=11..13,y=11..13,z=11..13".lineSequence(),-50..50))
       assertEquals(27+19-8,invoke("on x=10..12,y=10..12,z=10..12\non x=11..13,y=11..13,z=11..13\noff x=9..11,y=9..11,z=9..11".lineSequence(),-50..50))


    }

    @Test
    fun inputTestPart1Example() {
        assertEquals(590784, File("src/main/kotlin/aoc2021/input22example1.txt").useLines { invoke(it, -50..50) })
    }

    @Test
    fun inputTestPart1() {
        assertEquals(607657, File("src/main/kotlin/aoc2021/input22.txt").useLines { invoke(it, -50..50) })
    }

    @Test
    fun inputTestPart2aExample() {
        assertEquals(474140, File("src/main/kotlin/aoc2021/input22example2.txt").useLines { invoke(it, -50..50) })
    }

    @Test
    fun inputTestPart2Example() {
        assertEquals(2758514936282235, File("src/main/kotlin/aoc2021/input22example2.txt").useLines { invoke(it) })
    }

    @Test
    fun inputTestPart2() {
        assertEquals(1187742789778677, File("src/main/kotlin/aoc2021/input22.txt").useLines { invoke(it) })
    }



}