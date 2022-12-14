package aoc2022.day14

object Day202214 {

    const val Rock = 1
    const val Sand = 2
    data class Point(var x:Int, var y:Int) {
        fun lineTo(p1:Point):List<Point> {
            if (p1.y == y) {
                return (minOf(x,p1.x) .. maxOf(x,p1.x)).map { Point(it,y)}
            }
            else if (p1.x == x)
                return (minOf(y,p1.y) .. maxOf(y,p1.y)).map { Point(x,it)}
            return listOf()
        }
        fun sandAdjacent():List<Point> = listOf(Point(x,y+1),Point(x-1,y+1), Point(x+1, y+1))
    }
    class Cave(val map:MutableMap<Point, Int>) {

        val xMin = map.keys.minOf { it.x }
        val xMax = map.keys.maxOf { it.x }
        val yMin = map.keys.minOf { it.y }
        val yMax = map.keys.maxOf { it.y }


        fun addSand():Boolean {
            var p = Point(500,0)
            do {
                val n = p.sandAdjacent().firstOrNull { map[it] == null }
                if (n == null) {
                    map[p] = Sand
                    return true
                }
                if (n.y > yMax) return false
                p = n
            } while(true)
        }
        fun print() {

            println("\t$xMax .. $xMax")
            for (y in yMin .. yMax) {
                print(y); print("\t")
                for (x in xMin .. xMax) {
                    print(
                        when(map[Point(x, y)]) {
                            Rock -> "#"
                            Sand -> "o"
                            else -> "."
                        }
                    )
                }
                println()
            }
            println()
        }
    }
    fun part1(lines: Sequence<String>): Int {
        val cave = parseInput(lines)
        var i = 0
        while(cave.addSand()) i++
        cave.print()
        return i
    }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }

    fun parseInput(lines:Sequence<String>) : Cave {
        val map = mutableMapOf<Point,Int>()
        for (l in lines) {
            val points = l.split(" -> ").map {
                val p = it.split(",")
                Point(Integer.valueOf(p[0]), Integer.valueOf(p[1]))
            }
            for (i in 1 until points.size) {
                for (p in points[i-1].lineTo(points[i]))
                    map[p] = Rock
            }

        }
        return Cave(map)
    }
}