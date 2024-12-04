package aoc2024.day02

import kotlin.math.absoluteValue

object Day {

    fun part1(lines: List<String>): Int {
        val input = parseInput(lines)
        return input.count { isSave(it) }
    }
    fun part2(lines: List<String>): Int {
        val input = parseInput(lines)
        return input.count { isSave(it, true) }
    }
    private fun isSave(levels: List<Int>, mayRemoveLevel: Boolean=false): Boolean {

        val increaseCount = levels.indices.count { i -> i > 0 && levels[i] > levels[i-1] }
        val decreaseCount = levels.indices.count { i -> i > 0 && levels[i] < levels[i-1] }
        val diffCount = levels.indices.count { i -> i > 0 && (levels[i] - levels[i-1]).absoluteValue in 1..3 }
        if ((increaseCount == levels.size - 1 || decreaseCount == levels.size - 1) && diffCount == levels.size - 1) {
            return true
        }
        if (!mayRemoveLevel) return false
        levels.indices.forEach { i ->
            if (isSave(levels.filterIndexed { index, _ -> index != i }, false)) {
                return true
            }
        }
        return false
    }



    fun parseInput(lines: List<String>): List<List<Int>> = lines.map {
        it.split(" ").map { it.toInt() }
    }
}