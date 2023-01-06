package aoc2022.day23

import shared.CompassDirection
import shared.CompassDirection.*
import shared.Point
import shared.width

object Day202223 {

    class ElvesMap(val elves: MutableMap<Point, Boolean>) {
        fun getXRange() = IntRange(elves.keys.minOf { it.x }, elves.keys.maxOf { it.x })
        fun getYRange() = IntRange(elves.keys.minOf { it.y }, elves.keys.maxOf { it.y })
        fun get(p: Point) = elves[p]
        fun get(p: Point, direction: CompassDirection) = elves[p.to(direction)]
        fun elvesIn(p: Point, vararg direction: CompassDirection) = direction.any { get(p, it) != null }

        fun round(r:Int):Boolean {
            val proposals = mutableListOf<Pair<Point, Point>>()
            for (p in elves.keys) {
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
            var didSomething = false
            for (proposal in proposals) {
                if (proposals.none { it !== proposal && proposal.second == it.second }) {
                    elves[proposal.second] = true
                    elves.remove(proposal.first)
                    didSomething = true
                }
            }
            return didSomething
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
        val map = parseInput(lines)
        for (i in 0 .. Int.MAX_VALUE) {
            if (!map.round(i))
                return i + 1
        }
        return 0
    }

    fun parseInput(lines: Sequence<String>) = ElvesMap(lines.flatMapIndexed { y, row ->
        row.mapIndexedNotNull { x, c -> if (c == '#') Point(x, y) to true else null }
    }.toMap().toMutableMap())
}