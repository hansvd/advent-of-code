package aoc2024.day01

import kotlin.math.absoluteValue

object Day {


    fun part1(lines: List<String>): Int {
        val (left, right) = parseInput(lines)
        val lsort = left.sorted()
        val rsort = right.sorted()
        return (0 until lsort.size).sumOf { i ->
            (lsort[i] - rsort[i]).absoluteValue
        }
    }

    fun part2(lines: List<String>): Int {
        val (left, right) = parseInput(lines)
        return left.sumOf { l ->
            l * right.count { it == l }
        }
    }

    fun parseInput(lines: List<String>): Pair<List<Int>, List<Int>> {
        val result = lines.map { it.split("   ")
            .map(String::toInt) }
            .map { it[0] to it[1] }
        return result.map { it.first } to result.map { it.second }
    }
}