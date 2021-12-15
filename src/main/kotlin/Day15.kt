

object Day15 {
    fun day15(lines: Sequence<String>, repeat: Int = 1): Int {
        val map = Map(parseInput(lines),repeat)
        val to = Point(map.w - 1, map.h - 1)
        return dijkstraSearch(
            init = Step(Point(0, 0), 0, null),
            found = { step -> step.pos == to },
            children = { step -> step.pos.next(map.w, map.h)
                    .map { neighbor -> Step(neighbor, map.weight(neighbor), step) }
            },
            cost = { step -> step.totalRisk }
        ).first().totalRisk

    }
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



    class Map(private val rows:List<IntArray>, repeat:Int=1) {
        private val nw = rows[0].size  // non-repeat with
        private val nh = rows.size
        val w = rows[0].size*repeat
        val h = rows.size*repeat

        private fun weight(x: Int, y: Int): Int = ((rows[y % nh][x % nw] + x / nw + y / nh) - 1) % 9 + 1
        fun weight(p:Point):Int = weight(p.x,p.y)
    }


    private fun parseInput(lines: Sequence<String>):List<IntArray> {
        return lines.map { l ->
            l.map { it.code - '0'.code}.toIntArray()
        }.toList()
    }
}