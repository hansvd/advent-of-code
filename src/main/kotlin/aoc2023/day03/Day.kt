package aoc2023.day03

import shared.Point

class Day {

    data class Number(val id: Int, val value: Long)

    private val numbers = mutableMapOf<Point, Number>()
    private val symbols = mutableMapOf<Point, Char>()


    fun part1(lines: List<String>): Long {
        readInput(lines)
        return numbers.filter {
            it.key.borderWithDistance(1).any { adj ->
                symbols.contains(adj)
            }
        }.map { it.value }.distinct().sumOf { it.value }
    }

    fun part2(lines: List<String>): Long {
        readInput(lines)
        return symbols.map { symbol ->
            val numbers = symbol.key.borderWithDistance(1).mapNotNull {
                numbers[it]
            }.distinct().toList()
            if (numbers.size != 2) 0 else numbers[0].value * numbers[1].value
        }.sum()
    }

    fun readInput(lines: List<String>) {
        var id = 0
        lines.forEachIndexed { row, line ->
            var c = 0
            while (c < line.length) {
                if (line[c] == '.') {
                    c++
                    continue
                }
                if (!line[c].isDigit()) {
                    symbols[Point(c, row)] = line[c++]
                    continue
                }
                var cc = c
                while (cc < line.length && line[cc].isDigit()) cc++
                val n = line.substring(c, cc).toLong()
                val num = Number(id++, n)
                (c until cc).forEach { numbers[Point(it, row)] = num }
                c = cc
            }
        }
    }
}