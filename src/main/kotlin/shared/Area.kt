package shared

@Suppress("unused")
data class Area(val topLeft:Point, val bottomRight:Point) {
    fun isOverlap(l: HLine): Boolean {

        if ((l.y < topLeft.y) || l.y > bottomRight.y) return false
        return (topLeft.x .. bottomRight.x).isOverlap(l.x)
    }

    val left get() = topLeft.x
    val right get() = bottomRight.x
    val top get() = topLeft.y
    val bottom get() = bottomRight.y
    val yRange get() = topLeft.y .. bottomRight.y
    val xRange get() = topLeft.x .. bottomRight.x
    val width get() = bottomRight.x - topLeft.x + 1
    val height get() = bottomRight.y - topLeft.y + 1

}