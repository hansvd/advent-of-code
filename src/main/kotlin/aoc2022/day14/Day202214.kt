package aoc2022.day14

object Day202214 {

    const val Rock = 1
    const val Sand = 2
    data class Point(var x:Int, var y:Int) {
        fun hLineTo(p1:Point):List<Point> = when {
            p1.y == y -> (minOf(x,p1.x) .. maxOf(x,p1.x)).map { Point(it,y)}
            p1.x == x -> (minOf(y,p1.y) .. maxOf(y,p1.y)).map { Point(x,it)}
            else -> listOf()
        }
        fun sandAdjacent():List<Point> = listOf(Point(x,y+1),Point(x-1,y+1), Point(x+1, y+1))
    }
    class Cave(val map:MutableMap<Point, Int>, val hasBottom: Boolean) {

        private val yMax = map.keys.maxOf { it.y } + (if (hasBottom) 2 else 0)

        fun get(p:Point):Int = if (hasBottom && p.y == yMax) Rock else map[p] ?: 0

        fun addSand():Boolean {
            var p = Point(500,0)
            if (get(p) != 0) return false
            do {
                val n = p.sandAdjacent().firstOrNull { get(it) == 0 }
                if (n == null) {
                    map[p] = Sand
                    return true
                }
                if (n.y > yMax) return false
                p = n
            } while(true)
        }
    }
    fun part1(lines: Sequence<String>): Int = run(lines)

    fun part2(lines: Sequence<String>): Int = run(lines,true)
    private fun run(lines: Sequence<String>, hasBottom: Boolean=false): Int {
        val cave = parseInput(lines,hasBottom)
        var i = 0; while(cave.addSand()) i++
        return i
    }


    fun parseInput(lines:Sequence<String>, hasBottom:Boolean) : Cave {
        val map = mutableMapOf<Point,Int>()
        for (l in lines) {
            val points = l.split(" -> ").map {
                val p = it.split(",")
                Point(Integer.valueOf(p[0]), Integer.valueOf(p[1]))
            }
            (1 until points.size).forEach { i ->
                points[i-1].hLineTo(points[i]).forEach { p -> map[p] = Rock }
            }
        }
        return Cave(map, hasBottom)
    }
}