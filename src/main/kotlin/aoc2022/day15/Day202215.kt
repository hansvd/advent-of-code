package aoc2022.day15

import shared.Point
import shared.combine
import shared.isOverlap
import shared.width

object Day202215 {

    class Map(val ranges: List<IntRange>) {
        fun noBeaconCount(): Int {
            val result = mutableListOf<IntRange>()
            for (l in ranges.sortedBy { it.first }) {
                val ll = result.firstOrNull { l.isOverlap(it) }
                if (ll == null) result.add(l)
                else {
                    result.remove(ll)
                    result.add(l.combine(ll))
                }
            }
            return result.sumOf { it.width }
        }
    }

    fun part1(lines: Sequence<String>, testY: Int = 10): Int = parseInput(lines, testY).noBeaconCount()

    fun part2(lines: Sequence<String>): Int {
        return 0
    }

    private fun parseInput(lines: Sequence<String>, testY: Int): Map {
        val reg = """Sensor at x=(-?\d+), y=(-?\d+): closest beacon is at x=(-?\d*), y=(-?\d*)""".toRegex()
        val result = mutableListOf<IntRange>()
        lines.mapNotNull { l ->
            val match = reg.matchEntire(l)
            if (match != null)
                Pair(
                    Point(Integer.valueOf(match.groupValues[1]), Integer.valueOf(match.groupValues[2])),
                    Point(Integer.valueOf(match.groupValues[3]), Integer.valueOf(match.groupValues[4]))
                )
            else null
        }.forEach {

            if (it.first.y == testY) result.add(it.first.x..it.first.x)
            //if (it.second.y == testY) result.add(Line(it.second,it.second))
            val beacon = it.second

            val d = it.first.manhattanDistance(it.second)
            val l = it.first.adjacentWithManhattanDistanceForY(d, testY) ?: return@forEach
            if (beacon.y == testY && (l.left..l.right).contains(beacon.x)) {
                if (beacon.x == l.left)
                    result.add(l.left + 1..l.right)
                else
                    result.add(l.left until l.right)
            } else
                result.add(l.left..l.right)
        }

        return Map(result)
    }
}