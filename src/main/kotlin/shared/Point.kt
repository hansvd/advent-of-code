package shared

import shared.Direction.*
import kotlin.math.abs


data class Point(var x: Int, var y: Int) {

    fun to(direction: CompassDirection):Point {
        return when(direction) {
            CompassDirection.N -> Point(x,y-1)
            CompassDirection.E -> Point(x+1,y)
            CompassDirection.W -> Point(x-1,y)
            CompassDirection.S -> Point(x,y+1)
            CompassDirection.NE -> Point(x+1,y-1)
            CompassDirection.NW -> Point(x-1,y-1)
            CompassDirection.SE -> Point(x+1,y+1)
            CompassDirection.SW -> Point(x-1,y+1)
        }
    }
    fun manhattanDistance(other: Point) = abs(x - other.x) + abs(y - other.y)

    fun adjacentWithinManhattanDistance(d: Int): Sequence<Point> = sequence {
        for (yy in y - d..y + d) {
            val dy = abs(y - yy)
            val dx = d - dy
            for (xx in x - dx..x + dx) {
                yield(Point(xx, yy))
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
                val x1 = maxOf(x - dx, xRangeLimit.first)
                val x2 = minOf(x + dx, xRangeLimit.last)
                if (x1 <= x2)
                    HLine(x1..x2, yy)
                //HLine(x - dx..x + dx, yy)
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

    fun left() = Point(this.x - 1, y)
    fun right() = Point(this.x + 1, y)
    private fun up() = Point(x, this.y - 1)
    private fun down() = Point(x,this.y + 1)
    fun left(xRange:IntRange) = if (x - 1 < xRange.first) Point(xRange.last,y) else left()
    fun right(xRange:IntRange) = if (x + 1 > xRange.last) Point(xRange.first,y) else right()

    private fun up(yRange:IntRange) = if (y - 1 < yRange.first) Point(x, yRange.last) else up()
    private fun down(yRange:IntRange) = if (y + 1 > yRange.last) Point(x, yRange.first) else down()

    fun next(direction: Direction) = when(direction) {
        Right -> right()
        Left -> left()
        Down -> down()
        Up -> up()
    }
    fun next(direction: Direction, xRange: IntRange, yRange: IntRange) = when(direction) {
        Right -> right(xRange)
        Left -> left(xRange)
        Down -> down(yRange)
        Up -> up(yRange)
    }

    fun borderWithDistance(d: Int): Sequence<Point> = sequence {
        for (yy in y - d..y + d) {
            yield(Point(x - d, yy))
            yield(Point(x+d,yy))
        }
        for (xx in x - d + 1..x + d - 1) {
            yield(Point(xx, y - d))
            yield(Point(xx, y +d))
        }
    }
}

