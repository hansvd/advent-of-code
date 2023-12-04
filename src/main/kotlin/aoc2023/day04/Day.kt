package aoc2023.day04

import kotlin.math.pow

object Day {
    private fun Int.pow(exp:Int) = this.toDouble().pow(exp).toLong()

    data class Card(val winning:List<Int>, val our:List<Int>)
    fun part1(lines: List<String>) =
        parseInput(lines).map { card ->
            card.our.filter { card.winning.contains(it) }.size
        }.sumOf { if (it == 0) 0 else 2.pow(it - 1) }

    fun part2(lines: List<String>): Int = 0

    fun parseInput(lines: List<String>):List<Card> = lines.map { line ->
        val parts = line.split(":")[1].split('|')
        Card(parts[0].trim().split(' ').filter { it.isNotBlank() }.map { it.toInt() },
            parts[1].trim().split(' ').filter { it.isNotBlank() }.map { it.toInt() })
    }
}