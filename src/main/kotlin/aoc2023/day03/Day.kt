package aoc2023.day03

import shared.Area
import shared.Point

object Day {

    data class Number(val id:Int, val value:Long)
    val numbers2 = mutableMapOf<Point,Number>()
    val numbers = mutableMapOf<Point,Int>()
    val symbols = mutableMapOf<Point,Char>()

    private fun numberToArea(n:Map.Entry<Point,Int>) =
        Area(n.key,Point(n.key.x + n.value.toString().length-1, n.key.y))


    fun part1(lines: List<String>):Int {
        readInput(lines,false)
        return numbers.filter {
            val area = numberToArea(it)
            area.borderWithDistance(1).any { adj ->
                symbols.contains(adj)
            }
        }.map { it.value }.sum()
    }

    fun part2(lines: List<String>):Long {
        readInput(lines,true)
        return symbols.map { symbol ->
            val numbers = symbol.key.borderWithDistance(1).mapNotNull {
                numbers2[it]
            }.distinct().toList()
            if (numbers.size != 2) 0 else numbers[0].value * numbers[1].value
        }.sum()
    }

    fun readInput(lines: List<String>, part2:Boolean) {
        lines.forEachIndexed { row, line ->
            var c = 0
            var id = 0
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
                val n =  line.substring(c,cc).toInt()
                if (!part2)
                    numbers[Point(c,row)] = n
                else {
                    val num = Number(id++,n.toLong())
                    (c until cc).forEach { numbers2[Point(it,row)] = num }
                }
                c = cc
            }
        }
    }
}