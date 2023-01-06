package aoc2022.day23

import shared.CompassDirection
import shared.CompassDirection.*
import shared.Point
import shared.width

object Day202223 {

    class ElvesMap(val elves: MutableMap<Point, Int>) {
        fun getXRange() = IntRange(elves.keys.minOf { it.x }, elves.keys.maxOf { it.x })
        fun getYRange() = IntRange(elves.keys.minOf { it.y }, elves.keys.maxOf { it.y })
        fun get(p: Point) = elves[p]
        fun get(p: Point, direction: CompassDirection) = elves[p.to(direction)]
        fun elvesIn(p: Point, vararg direction: CompassDirection) = direction.any { get(p, it) != null }

        fun round(r:Int) {
            val proposals = mutableListOf<Pair<Point, Point>>()
            for ((p, elf) in elves.entries) {
                if (!elvesIn(p, N, S, E, W, NE, NW, SW, SE))
                    continue
                val m = listOf(
                    arrayOf(N, NE, NW) to N,
                    arrayOf(S, SE, SW) to S,
                    arrayOf(W,NW,SW) to W,
                    arrayOf(E,NE,SE) to E
                )
                for (i in 0..3) {
                    val index = (i + r) % 4
                    if (!elvesIn(p,*m[index].first)) {
                        proposals.add(p to p.to(m[index].second))
                        break
                    }
                }
            }
            for (proposal in proposals) {
                if (proposals.none { it !== proposal && proposal.second == it.second }) {
                    elves[proposal.second] = elves[proposal.first]!! + 1
                    elves.remove(proposal.first)
                }
            }
        }

        fun print() {
            for (y in getYRange()) {
                for (x in getXRange()) {
                    print(get(Point(x, y))?.toString() ?: ".")
                }
                println()
            }
            println("\n----\n")
        }
        fun countEmptyTiles():Int = getYRange().width * getXRange().width - elves.count()
    }

    fun part1(lines: Sequence<String>): Int {
        val map = parseInput(lines)
        repeat(10) {
            map.round(it)
            //map.print()
        }
        return map.countEmptyTiles()
    }


    fun part2(lines: Sequence<String>): Int {
        return 0
    }

    fun parseInput(lines: Sequence<String>) = ElvesMap(lines.flatMapIndexed { y, row ->
        row.mapIndexedNotNull { x, c -> if (c == '#') Point(x, y) to 0 else null }
    }.toMap().toMutableMap())
}