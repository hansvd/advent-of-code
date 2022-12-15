package shared

import kotlin.math.abs

data class Line(val p0: Point, val p1: Point) {
    val left = minOf(p0.x, p1.x)
    val right = maxOf(p0.x, p1.x)
}

data class Point(var x: Int, var y: Int) {
    //    fun topLeft(other: Point): Point = Point(minOf(x, other.x), minOf(y, other.y))
//    fun bottomRight(other: Point): Point = Point(maxOf(x, other.x), maxOf(y, other.y))
    fun manhattanDistance(other: Point) = abs(x - other.x) + abs(y - other.y)

//    fun hLineTo(p1: Point): List<Point> = when {
//        p1.y == y -> (minOf(x, p1.x)..maxOf(x, p1.x)).map { Point(it, y) }
//        p1.x == x -> (minOf(y, p1.y)..maxOf(y, p1.y)).map { Point(x, it) }
//        else -> listOf()
//    }

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

//    fun adjacentWithManhattanDistanceAsHLines(d: Int): Sequence<Line> {
//        return sequence {
//
//            for (yy in y - d..y + d) {
//                val dy = abs(y - yy)
//                val dx = d - dy
//                yield(Line(Point(x - dx, yy), Point(x + dx, yy)))
//            }
//        }
//    }

    fun adjacentWithManhattanDistanceForY(d: Int, yy: Int): Line? {
        val dy = abs( y - yy)
        val dx = d - dy
        if (dx < 0) return null
        return Line(Point(x - dx, y), Point(x + dx, y))
    }
}

