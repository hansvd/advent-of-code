import nl.avwie.aoc.common.search.dijkstraSearch

object Day15 {
    data class Point(val x:Int, val y:Int) {
        fun next(w:Int,h:Int):Set<Point> {
            return listOf(Point(x-1,y),Point(x+1,y),Point(x,y-1),Point(x,y+1))
                .filter { it.x >=0 && it.y >= 0 && it.x < w && it.y < h }.toSet()
        }
    }

    data class Step(val pos: Point, val risk: Int, val previous: Step?) {
        override fun equals(other: Any?): Boolean = (other as? Step)?.pos == pos
        override fun hashCode(): Int = pos.hashCode()
        val totalRisk: Int = (previous?.totalRisk ?: 0) + risk
    }

    fun lowestRisk(input: Map): Int = Point(input.w-1,input.h-1).let { target ->
        dijkstraSearch(
            init = Step(Point(0, 0), 0, null),
            found = { step -> step.pos == target },
            children = { step ->
                step.pos.next(input.w,input.h)
                    .map { neighbor -> Step(neighbor, input.weight(neighbor), step) }
            },
            cost = { step -> step.totalRisk }
        ).first().totalRisk
    }

    class Map(val rows:List<IntArray>, val repeat:Int=1):Graph<Point> {
        val nw = rows[0].size  // non-repeat with
        val nh = rows.size
        val w = rows[0].size*repeat
        val h = rows.size*repeat
        override val vertices: Set<Point>
            = (0 until w).map { x-> (0 until h).map { y-> Point(x,y)} }.flatten().toSet()

        override fun edges(t: Point): Set<Point>? {
             return t.next(w,h)
        }

        fun weight(x: Int, y: Int): Int {
            return ((rows[y % nh][x % nw] + x / nw + y / nh) - 1) % 9 + 1
        }
        fun weight(p:Point):Int = weight(p.x,p.y)

        override fun weights(t: Pair<Point, Point>): Int {
            return weight(t.second.x,t.second.y)
        }

        fun cost(path:List<Point>):Int = path.drop(1).sumOf { weight(it.x,it.y) }
    }

    fun day15(lines: Sequence<String>, repeat: Int=1): Int {

        val graph = Map(parseInput(lines),repeat)
        return lowestRisk(graph)
//        val shortestPathTree = dijkstra(graph,Point(0,0))
//        val shortestPath = shortestPath(shortestPathTree, Point(0,0), Point(graph.w-1, graph.h-1))
//        return graph.cost(shortestPath)

    }


    fun parseInput(lines: Sequence<String>):List<IntArray> {
        return lines.map { l ->
            l.map { it.code - '0'.code}.toIntArray()
        }.toList()
    }
}