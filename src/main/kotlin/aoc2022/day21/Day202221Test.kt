package aoc2022.day21

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertEquals

class Day202221Test {

    val exInput = """root: pppw + sjmn
dbpl: 5
cczh: sllz + lgvd
zczc: 2
ptdq: humn - dvpt
dvpt: 3
lfqf: 4
humn: 5
ljgn: 2
sjmn: drzm * dbpl
sllz: 4
pppw: cczh / lfqf
lgvd: ljgn * ptdq
drzm: hmdt - zczc
hmdt: 32"""

    @Test
    fun part1ExTest() {
        assertEquals(152, Day202221.part1(exInput.lineSequence()))
    }

    @Test
    fun part1Test() {
        assertEquals(194501589693264,useLines(2022,21) { Day202221.part1(it) })
    }


    @Test
    fun part2ExTest() {
        assertEquals(301, Day202221.part2(exInput.lineSequence()))
    }

    @Test
    fun part2Test() {
        assertEquals(0,useLines(2022,21) { Day202221.part2(it) })
    }
}