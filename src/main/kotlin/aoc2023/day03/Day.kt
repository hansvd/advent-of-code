package aoc2023.day03

import shared.Area
import shared.Point

object Day {

    val numbers = mutableMapOf<Point,Int>()
    val symbols = mutableMapOf<Point,Char>()

    fun part1(lines: List<String>):Int {
        readInput(lines)
        return numbers.filter {
            val area = Area(it.key,Point(it.key.x + it.value.toString().length-1, it.key.y))
            area.borderWithDistance(1).any { adj ->
                symbols.contains(adj)
            }
        }.map { it.value }.sum()
    }

    fun part2(lines: List<String>): Int = 0

    fun readInput(lines: List<String>) {
        lines.forEachIndexed { row, line ->
            var c = 0
            while (c < line.length) {
                if (line[c] == '.') {
                    c++
                    continue
                }
                if (!line[c].isDigit()) {
                    symbols[Point(c,row)] = line[c++]
                    continue
                }
                var cc = c
                while (cc < line.length && line[cc].isDigit()) cc++
                numbers[Point(c,row)] = line.substring(c,cc).toInt()
                c = cc
            }
        }
    }
}