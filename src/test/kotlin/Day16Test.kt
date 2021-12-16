import Day12.Companion.part2
import Day16.Companion.hexToBinString
import Day16.Companion.part1

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day16Test {

    val testInput = """"""

    @Test
    fun day16aBaseTest() {
        assertEquals("110100101111111000101000",hexToBinString("D2FE28"))
        assertEquals(6, part1("D2FE28"))  // 2021
        assertEquals(9,part1("38006F45291200"))
        assertEquals(14,part1("EE00D40C823060"))
        assertEquals(16,part1("8A004A801A8002F478"))
        assertEquals(12,part1("620080001611562C8802118E34"))
        assertEquals(23,part1("C0015000016115A2E0802F182340"))
        assertEquals(31,part1("A0016C880162017C3686B18A3D4780"))
    }

    @Test
    fun dayXaTest() {
        assertEquals(923, part1(File("input/input16.txt").readText()))
    }

    @Test
    fun dayXbBaseTest() {
        assertEquals(0, part2("".lineSequence()))
    }

    @Test
    fun dayXbInputTest() {
        assertEquals(0, part2(testInput.lineSequence()))
    }

    @Test
    fun dayXbTest() {
        assertEquals(0, File("input/input16.txt").useLines { part2(it) })
    }


}