package aoc2022.day04

import shared.contains
import shared.isOverlap

object Day202204 {

    fun part1(lines: Sequence<String>): Int {
        return lines.map { parseLine(it) }.count {
            it.first.contains(it.second) || it.second.contains(it.first)
        }
    }

    fun part2(lines: Sequence<String>): Int {
        return lines.map { parseLine(it) }.count {
            it.first.isOverlap(it.second)
        }
    }

    private fun parseLine(line:String):Pair<IntRange,IntRange> {
        val r = line.split(',').map {r ->
            r.split('-').let {
                IntRange(it[0].toInt(),it[1].toInt())
        } }
        return Pair(r[0],r[1])
    }
}