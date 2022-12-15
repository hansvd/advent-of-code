package shared

import kotlin.math.abs

data class Point(var x: Int, var y: Int) {

    fun manhattanDistance(other: Point) = abs(x - other.x) + abs(y - other.y)

    fun adjacentWithManhattanDistance(d: Int): Sequence<Point> {
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

//    fun adjacentWithManhattanDistanceForYRange(d: Int, yRange: IntRange): List<HLine> {
//        val maxRange = y - d..y + d
//        val range = yRange.intersect(maxRange)
//        return range.mapNotNull { yy ->
//            val dy = abs(y - yy)
//            val dx = d - dy
//            if (dx < 0) null
//            HLine(x - dx .. x + dx, y)
//        }
//    }

    fun adjacentWithManhattanDistanceFor(d: Int, area: Area): List<HLine> {
        val yRange = y - d..y + d
        val xRange = x - d..x + d
        val yRangeResult = area.yRange.intersect(yRange)
        val xRangeResult = area.xRange.intersect(xRange)
        return yRangeResult.mapNotNull { yy ->
            val dy = abs(y - yy)
            val dx = d - dy
            if (dx < 0) null
            else {
                val x1 = maxOf(x - d, xRangeResult.first)
                val x2 = minOf(x + d, xRangeResult.last)
                if (x1 <= x2)
                    HLine(x - dx..x + dx, yy)
                else null
            }
        }
    }

    fun adjacentWithManhattanDistanceForY(d: Int, yy: Int): HLine? {
        val dy = abs(y - yy)
        val dx = d - dy
        if (dx < 0) return null
        return HLine(x - dx..x + dx, yy)
    }
}

