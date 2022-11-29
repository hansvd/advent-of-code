package aoc2021

import aoc2021.Advent8.advent8a
import aoc2021.Advent8.advent8b
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day8Test {

    val testInput = """be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb |fdgacbe cefdb cefbgd gcbe
edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec |fcgedb cgb dgebacf gc
fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef |cg cg fdcagb cbg
fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega |efabcd cedba gadfec cb
aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga |gecf egdcabf bgf bfgea
fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf |gebdcfa ecba ca fadegcb
dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf |cefg dcbef fcge gbcadfe
bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd |ed bcgafe cdgba cbgef
egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg |gbdfcae bgc cg cgb
gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc |fgae cfgab fg bagce"""


    @Test
    fun advent8aInputTest() {
        assertEquals(26, advent8a(testInput.lineSequence()))
    }

    @Test
    fun advent8aTest() {
        assertEquals(387, File("src/main/kotlin/aoc2021/input8.txt").useLines { advent8a(it) })
    }

    @Test
    fun advent8bBaseTest() {
        //assertEquals(0, advent8b("abcefg cf acdeg acdfg bcdf abdfg abdefg acf abcdefg abcdfg|abcefg cf acdeg acdfg".lineSequence()))
        assertEquals(5353, advent8b("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf".lineSequence()))
    }

    @Test
    fun advent8bInputTest() {
        assertEquals(61229, advent8b(testInput.lineSequence()))
    }

    @Test
    fun advent8bTest() {
        assertEquals(986034, File("src/main/kotlin/aoc2021/input8.txt").useLines { advent8b(it) })
    }
}