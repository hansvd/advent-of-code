import Day16.Companion.hexToBinString
import Day16.Companion.part1
import Day16.Companion.part2
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
    fun day16aTest() {
        assertEquals(923, part1(File("input/input16.txt").readText()))
    }

    @Test
    fun day16bBaseTest() {
        assertEquals(3, part2("C200B40A82"))
        assertEquals(54,part2("04005AC33890"))
        assertEquals(7,part2("880086C3E88112"))
        assertEquals(9,part2("CE00C43D881120"))
        assertEquals(1,part2("D8005AC2A8F0"))
        assertEquals(0,part2("F600BC2D8F"))
        assertEquals(0,part2("9C005AC2F8F0"))
        assertEquals(1,part2("9C0141080250320F1802104A08"))
    }

    @Test
    fun day16bTest() {
        assertEquals(258888628940, part2(File("input/input16.txt").readText()))
    }


}