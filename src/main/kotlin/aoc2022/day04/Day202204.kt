package aoc2022.day02

import shared.contains

object Day202204 {

    fun part1(lines: Sequence<String>): Int {
        return lines.map { parseLine(it) }.count {
            it.first.contains(it.second) || it.second.contains(it.first)
        }
    }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }

    private fun parseLine(line:String):Pair<IntRange,IntRange> {
        val r = line.split(',').map {
            val n = it.split('-')
            IntRange(n[0].toInt(),n[1].toInt())
        }
        return Pair(r[0],r[1])
    }
}