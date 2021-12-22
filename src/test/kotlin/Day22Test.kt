
import Day22.invoke
import Day22.overlap
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day22Test {


    @Test
    fun exampleInputTestinvoke() {

        assertEquals(IntRange(-41,9), IntRange(-41,9).overlap(-50..50))
        assertEquals(IntRange(-41,9), IntRange(-41,9).overlap(-50..50))
        assertEquals(-50..50, (-54112..39298).overlap(-50..50))
        assertEquals(-50..-50, (-50..-50).overlap(-50..50))
        assertEquals(50..50, (50..50).overlap(-50..50))
        assertEquals(-50..50, (-150..150).overlap(-50..50))


        assertEquals(27,invoke("on x=10..12,y=10..12,z=10..12".lineSequence(),-50..50))
        assertEquals(27+19,invoke("on x=10..12,y=10..12,z=10..12\non x=11..13,y=11..13,z=11..13".lineSequence(),-50..50))
        //assertEquals(27+19-8,invoke("on x=10..12,y=10..12,z=10..12\non x=11..13,y=11..13,z=11..13\noff x=9..11,y=9..11,z=9..11".lineSequence(),-50..50))


    }

    @Test
    fun inputTestinvokeExample() {
        assertEquals(590784, File("input/input22example1.txt").useLines { invoke(it, -50..50) })
    }

    @Test
    fun inputTestinvoke() {
        assertEquals(607657, File("input/input22.txt").useLines { invoke(it, -50..50) })
    }

    @Test
    fun inputTestPart2aExample() {
        assertEquals(474140, File("input/input22example2.txt").useLines { invoke(it, -50..50) })
    }

    @Test
    fun inputTestPart2Example() {
        assertEquals(2758514936282235, File("input/input22example2.txt").useLines { invoke(it) })
    }

    @Test
    fun inputTestPart2() {
        assertEquals(0, File("input/input22.txt").useLines { invoke(it) })
    }



}