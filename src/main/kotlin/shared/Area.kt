package shared

@Suppress("unused")
data class Area(val topLeft:Point, val bottomRight:Point) {
    fun isOverlap(l: HLine): Boolean {

        if ((l.y < topLeft.y) || l.y > bottomRight.y) return false
        return (topLeft.x .. bottomRight.x).isOverlap(l.x)
    }
    fun contains(p:Point) = p.x >= left && p.y >= top && p.x <= right && p.y <= bottom

    fun manhattanDistance(p:Point):Int {
        val dx = if (p.x < left) left - p.x else if (p.x > right) p.x - right else 0
        val dy = if (p.y < top) top - p.y else if (p.y > bottom) p.y - bottom else 0
        return dx + dy
    }
    val left get() = topLeft.x
    val right get() = bottomRight.x
    val top get() = topLeft.y
    val bottom get() = bottomRight.y
    val yRange get() = topLeft.y .. bottomRight.y
    val xRange get() = topLeft.x .. bottomRight.x
    val width get() = bottomRight.x - topLeft.x + 1
    val height get() = bottomRight.y - topLeft.y + 1

    fun adjacentWithinManhattanDistance(d: Int): Sequence<Point> = sequence {
        for (yy in top - d..bottom + d) {
            for (xx in left - d..right + d) {
                if (manhattanDistance(Point(xx,yy)) == d)
                    yield(Point(xx, yy))
            }
        }
    }
    fun borderWithDistance(d: Int): Sequence<Point> = sequence {
        for (yy in top - d..bottom + d) {
            yield(Point(left - d, yy))
            yield(Point(right+d,yy))
        }
        for (xx in left - d..right + d) {
            yield(Point(xx, top - d))
            yield(Point(xx, bottom +d))
        }
    }

}