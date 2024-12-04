package aoc2024.day02

import kotlin.math.absoluteValue

object Day {

    fun part1(lines: List<String>): Int = parseInput(lines).count { isSave(it) }
    fun part2(lines: List<String>): Int = parseInput(lines).count { isSavePart2(it) }

    private fun isSavePart2(levels: List<Int>): Boolean =
        isSave(levels) || levels.indices.any { i -> isSave(levels.filterIndexed { index, _ -> index != i }) }

    private fun isSave(levels: List<Int>): Boolean = levels.zipWithNext().let { zipped ->
        (zipped.count { (prev, current) -> (prev - current).absoluteValue in 1..3 } == zipped.size) &&
         ((zipped.count { (prev, current) -> current > prev } == zipped.size) ||
          (zipped.count { (prev, current) -> current < prev } == zipped.size))
    }

    fun parseInput(lines: List<String>): List<List<Int>> = lines.map {
        it.split(" ").map { it.toInt() }
    }
}