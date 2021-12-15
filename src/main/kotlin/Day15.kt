import Day15.part1
import Day15.part2
import java.io.File

fun main() {
    File("input/input15.txt").useLines {
        println(part1(it))
        println(part2(it))
    }
}

object Day15 {
    data class Point(val x:Int, val y:Int) {
        fun next(w:Int,h:Int):Set<Point> {
            return listOf(Point(x-1,y),Point(x+1,y),Point(x,y-1),Point(x,y+1))
                .filter { it.x >=0 && it.y >= 0 && it.x < w && it.y < h }.toSet()
        }
    }
    class Map(val rows:List<IntArray>):Graph<Point> {
        val w = rows[0].size
        val h = rows.size


        override val vertices: Set<Point>
            = (0 until w).map { x-> (0 until h).map { y-> Point(x,y)} }.flatten().toSet()

        override fun edges(t: Point): Set<Point>? {
             return t.next(w,h)
        }

        override fun weights(t: Pair<Point, Point>): Int {
            return rows[t.second.y][t.second.x]
        }

        fun cost(path:List<Point>):Int = path.drop(1).sumOf { rows[it.y][it.x] }


    }
    fun part1(lines: Sequence<String>): Int {

        val graph = parseInput(lines)
        val shortestPathTree = dijkstra(graph,Point(0,0))
        val shortestPath = shortestPath(shortestPathTree, Point(0,0), Point(graph.w-1, graph.h-1))
        return graph.cost(shortestPath)

    }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }

    fun parseInput(lines: Sequence<String>):Map {
        return Map(lines.map { l ->
            l.map { it.code - '0'.code}.toIntArray()
        }.toList())
    }
}