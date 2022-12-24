package aoc2022.day20

import org.junit.jupiter.api.Test
import shared.Input.useLines
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class Day202220Test {

    val exInput = "1\n2\n-3\n3\n-2\n0\n4"

    @Test
    fun part1Test1() {
        val test = listOf(1, 2, -3, 3, -2, 0, 4).mapIndexed { index, i -> Pair(index, i) }.toMutableList()
        Day202220.move(test, 0)
        assertContentEquals(listOf(2, 1, -3, 3, - 2, 0, 4), test.map { it.second })
    }

    @Test
    fun part1Test1a() {
        val test = listOf(1, 6, -3, 3, -2, 0, 4).mapIndexed { index, i -> Pair(index, i) }.toMutableList()
        Day202220.move(test, 1)
        assertContentEquals(listOf(1, 6, -3, 3, - 2, 0, 4), test.map { it.second })
    }

    @Test
    fun part1Test2() {
        val test = listOf(6, 2, -3, 3, -2, 0, 4).mapIndexed { index, i -> Pair(index, i) }.toMutableList()
        Day202220.move(test, 0)
        assertContentEquals(listOf(6, 2, -3, 3, - 2, 0, 4), test.map { it.second })
    }

    @Test
    fun part1Test3() {
        val test = listOf(12, 2, -3, 3, -2, 0, 4).mapIndexed { index, i -> Pair(index, i) }.toMutableList()
        Day202220.move(test, 0)
        assertContentEquals(listOf(12, 2, -3, 3, - 2, 0, 4), test.map { it.second })
    }

    @Test
    fun part1Test5() {
        val test = listOf(2, -1, -3, 3, -2, 0, 4).mapIndexed { index, i -> Pair(index, i) }.toMutableList()
        Day202220.move(test, 1)
        assertContentEquals(listOf(-1, 2, -3, 3, - 2, 0, 4), test.map { it.second })
    }

    @Test
    fun part1Test6() {
        val test = listOf(2, -8, -3, 3, -2, 0, 4).mapIndexed { index, i -> Pair(index, i) }.toMutableList()
        Day202220.move(test, 1)
        assertContentEquals(listOf(2, -3, 3, - 2, 0, -8, 4), test.map { it.second })
    }
    @Test
    fun part1Test7() {
        val test = listOf(2, -12, -3, 3, -2, 0, 4).mapIndexed { index, i -> Pair(index, i) }.toMutableList()
        Day202220.move(test, 1)
        assertContentEquals(listOf(2, -12,-3, 3, - 2, 0, 4), test.map { it.second })
    }
    @Test
    fun part1Test0() {
        val test = listOf(0, 2, -3, 3, -2, 0, 4).mapIndexed { index, i -> Pair(index, i) }.toMutableList()
        Day202220.move(test, 0)
        assertContentEquals(listOf(0,2, -3, 3, - 2, 0, 4), test.map { it.second })
    }

    @Test
    fun part1TestNeg1() {
        val test = listOf(-1, 2, -3, 3, -2, 0, 4).mapIndexed { index, i -> Pair(index, i) }.toMutableList()
        Day202220.move(test, 0)
        assertContentEquals(listOf(2, -3, 3, - 2, 0, -1, 4), test.map { it.second })
    }

    @Test
    fun part1TestNeg2() {
        val test = listOf(-6, 2, -3, 3, -2, 0, 4).mapIndexed { index, i -> Pair(index, i) }.toMutableList()
        Day202220.move(test, 0)
        assertContentEquals(listOf(-6,2, -3, 3, - 2, 0, 4), test.map { it.second })
    }

    @Test
    fun part1TestNeg3() {
        val test = listOf(-7, 2, -3, 3, -2, 0, 4).mapIndexed { index, i -> Pair(index, i) }.toMutableList()
        Day202220.move(test, 0)
        assertContentEquals(listOf(2, -3, 3, - 2, 0,-7, 4), test.map { it.second })
    }

    @Test
    fun part1ExTest() {
        assertEquals(3, Day202220.part1(exInput.lineSequence()))
    }

    // > 9459
    @Test
    fun part1Test() {
        assertEquals(11073, useLines(2022, 20) { Day202220.part1(it) })
    }


    @Test
    fun part2ExTest() {
        assertEquals(0, Day202220.part2(exInput.lineSequence()))
    }

    @Test
    fun part2Test() {
        assertEquals(0, useLines(2022, 20) { Day202220.part2(it) })
    }
}