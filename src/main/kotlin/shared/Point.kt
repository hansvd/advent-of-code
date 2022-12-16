package shared

import kotlin.math.abs

data class Point(var x: Int, var y: Int) {

    fun manhattanDistance(other: Point) = abs(x - other.x) + abs(y - other.y)

    fun adjacentWithinManhattanDistance(d: Int): Sequence<Point> {
        return sequence {
            for (yy in y - d..y + d) {
                val dy = abs(y - yy)
                val dx = d - dy
                for (xx in x - dx..x + dx) {
                    yield(Point(xx, yy))
                }
            }
        }
    }

    fun adjacentWithinManhattanDistanceFor(d: Int, areaLimit: Area): List<HLine> {
        val yRange = y - d..y + d
        val xRange = x - d..x + d
        val yRangeLimit = areaLimit.yRange.intersect(yRange)
        val xRangeLimit = areaLimit.xRange.intersect(xRange)
        return yRangeLimit.mapNotNull { yy ->
            val dy = abs(y - yy)
            val dx = d - dy
            if (dx < 0) null
            else {
                val x1 = maxOf(x - d, xRangeLimit.first)
                val x2 = minOf(x + d, xRangeLimit.last)
                if (x1 <= x2)
                    HLine(x1..x2, yy)
                else null
            }
        }
    }

    fun adjacentWithinManhattanDistanceForY(d: Int, yy: Int): HLine? {
        val dy = abs(y - yy)
        val dx = d - dy
        if (dx < 0) return null
        return HLine(x - dx..x + dx, yy)
    }
}

