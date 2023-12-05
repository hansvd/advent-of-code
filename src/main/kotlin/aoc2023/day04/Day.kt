package aoc2023.day04

import kotlin.math.min
import kotlin.math.pow

object Day {
    private fun Int.pow(exp:Int) = this.toDouble().pow(exp).toLong()

    data class Card(val index:Int, val winning:List<Int>, val our:List<Int>)
    fun part1(lines: List<String>) =
        parseInput(lines).map { card ->
            card.our.filter { card.winning.contains(it) }.size
        }.sumOf { if (it == 0) 0 else 2.pow(it - 1) }

    fun part2(lines: List<String>):Int {
        val cards = parseInput(lines)
        val cardNumber = Array(cards.size) { 1}
        cards.forEachIndexed { index, card ->
            val s = card.our.filter { card.winning.contains(it) }.size
            (card.index + 1..min(card.index + s, cards.size - 1)).forEach {
                cardNumber[it] += cardNumber[index]
            }
        }
        return cardNumber.sum()
    }

    fun parseInput(lines: List<String>):List<Card> = lines.mapIndexed{ index, line ->
        val parts = line.split(":")[1].split('|')
        Card(index, parts[0].trim().split(' ').filter { it.isNotBlank() }.map { it.toInt() },
            parts[1].trim().split(' ').filter { it.isNotBlank() }.map { it.toInt() })
    }
}