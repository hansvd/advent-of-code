package aoc2022.day15

import shared.*

object Day202215 {

    class Input(val sensor: Point, val beacon: Point) {
        val d = sensor.manhattanDistance(beacon)

        fun noBeaconXRange(testY: Int): IntRange? {
            val l = sensor.adjacentWithinManhattanDistanceForY(d, testY) ?: return null
            return if (beacon.y == testY && (l.left..l.right).contains(beacon.x)) {
                if (beacon.x == l.left)
                    return l.left + 1..l.right
                else
                    l.left until l.right
            } else
                l.left..l.right
        }
    }

    fun part1(lines: Sequence<String>, testY: Int = 10): Int {
        val result = mutableListOf<IntRange>()
        parseInput(lines).mapNotNull { it.noBeaconXRange(testY) }.sortedBy { it.first }.forEach { l ->
            val ll = result.firstOrNull { l.isOverlap(it) }
            if (ll == null) result.add(l)
            else {
                result.remove(ll)
                result.add(l.combine(ll))
            }
        }
        return result.sumOf { it.width }
    }

    fun part2(lines: Sequence<String>, testRange: IntRange): Long {
        val searchArea = Area(Point(testRange.first, testRange.first), Point(testRange.last, testRange.last))
        val input = parseInput(lines).toList()

        // search in points just outside the distance
        // should be d + 1 because there should be only 1 result according to text,
        // (when d+2 there are multiple results)
        val r = input.asSequence().flatMap {
            it.sensor.adjacentWithinManhattanDistanceFor(it.d + 1, searchArea)
        }
            .flatMap { listOf(Point(it.left, it.y), Point(it.right, it.y)) }
            .first { outerPoint ->
            input.all {
                it.sensor.manhattanDistance(outerPoint) > it.d
            }
        }
        return r.x.toLong() * 4000000L + r.y
    }


    private val reg = """Sensor at x=(-?\d+), y=(-?\d+): closest beacon is at x=(-?\d*), y=(-?\d*)""".toRegex()
    private fun parseInput(lines: Sequence<String>) = lines.mapNotNull { l ->
        val match = reg.matchEntire(l)
        if (match != null)
            Input(
                Point(Integer.valueOf(match.groupValues[1]), Integer.valueOf(match.groupValues[2])),
                Point(Integer.valueOf(match.groupValues[3]), Integer.valueOf(match.groupValues[4]))
            )
        else null
    }
}