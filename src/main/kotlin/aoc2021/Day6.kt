package aoc2021

import aoc2021.Advent6.advent6
import java.io.File

fun main() {
    val input = File("src/main/kotlin/aoc2021/input6.txt").readText()

    println(advent6(input))
    println(advent6(input, 256))

}

object Advent6 {
    data class Fish(val d: Int, val Nb: Long)

    fun advent6(line: String, days: Int = 80): Long {
        val start = line.split(',').mapNotNull { tryParseInt(it) }.map { Fish(it, 1) }
        return (1..days).fold(start) { cur,_ -> nextDay(cur) }.sumOf { it.Nb }
    }


    private fun nextDay(list: List<Fish>): List<Fish> =
        list.flatMap { f -> if (f.d > 0) listOf(Fish(f.d - 1, f.Nb)) else listOf(Fish(6, f.Nb), Fish(8, f.Nb)) }
            .groupBy { it.d }.map { (d, fishes) ->
                Fish(d, fishes.sumOf { it.Nb })
            }
}