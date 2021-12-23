import kotlin.math.abs

object Day23 {
    data class Point(val x:Int, val y:Int) {
        val above get() = Point(x,y-1)
        val below get() = Point(x,y+1)
    }

    fun List<String>.getFreeSpaces():List<Point> =
        this.mapIndexed { r, s -> s.mapIndexedNotNull{ c, ch  -> if (ch == '.') Point(c,r) else null } }.flatten()
    fun List<String>.getAmphipods():List<Point> =
        this.mapIndexed { r, s -> s.mapIndexedNotNull{ c, ch  -> if (ch in 'A'..'D') Point(c,r) else null } }.flatten()
    fun List<String>.print() {
        println("--")
        for (r in this) println(r)
    }
    class Burrow(val rows: List<String>) {
        val width = rows.maxOf { it.length }
        val hallway = rows.getFreeSpaces()
        val sideRooms = rows.getAmphipods()

    }
    data class BurrowState(val burrow: Burrow, val previous:BurrowState?, val rows: List<String>, val costs: Int = 0) {

        init {
            rows.print()
        }
        fun contains(state:BurrowState):Boolean {
            if (previous == null) return false
            if (previous == state) return true
            return previous.contains(state)
        }
        fun space(p:Point) = space(p.x,p.y)
        fun space(x:Int, y:Int):Char {
            if (x < 0 || y < 0 || y >= rows.size) return '#'
            val r = rows[y]
            if (x >= r.length) return '#'
            return r[x]
        }

        fun move(p1:Point,p2:Point):BurrowState {
            val s = space(p1)
            val newRows = rows.mapIndexed{ y,row -> if (y != p2.y) row else row.substring(0,p2.x) + s + row.substring(p2.x+1) }
                              .mapIndexed{ y,row -> if (y != p1.y) row else row.substring(0,p1.x) + '.' + row.substring(p1.x+1) }
            return this.copy(rows = newRows, previous = this, costs = costs + 100)
        }
        fun isValidMove(p1:Point, p2:Point):Boolean {

            // one pos at the time
            if (p1.y == p2.y) {
                if (abs(p1.x - p2.x) != 1) return false
            } else if (abs(p1.y - p2.y) != 1) return false

            val n = move(p1,p2)
            return previous?.contains(n) ?: true
        }

        val isFinish:Boolean by lazy {
            burrow.sideRooms.all {s -> this.space(s) in 'A'..'D' }
                    && burrow.sideRooms.all { s -> space(s) == space(s.above) || space(s) == space(s.below) }
        }

        fun next():List<BurrowState> {
            // find all amphipods that can move and then all possible moves
            val amphipods = rows.getAmphipods()
            val freeSpaces = rows.getFreeSpaces()
            return amphipods.map { a -> freeSpaces.filter { f -> isValidMove(a,f)}.map { f -> move(a,f)} }.flatten()
        }
//        companion object {
//            fun createFromInput(rows:List<String>) = BurrowState(rows, getHallwayFromInit(rows), sideRooms = getAmphipods(rows))
//            private fun getHallwayFromInit(rows:List<String>):List<Point> =
//                rows.mapIndexed { r, s -> s.mapIndexedNotNull{ c, ch  -> if (ch == '.') Point(c,r) else null } }.flatten()
//            private fun getAmphipods(rows:List<String>):List<Point> =
//                rows.mapIndexed { r, s -> s.mapIndexedNotNull{ c, ch  -> if (ch in 'A'..'D') Point(c,r) else null } }.flatten()
//            private fun getFreeSpaces(rows:List<String>):List<Point> =
//                rows.mapIndexed { r, s -> s.mapIndexedNotNull{ c, ch  -> if (ch == '.') Point(c,r) else null } }.flatten()
//        }
    }


    fun part1(lines: Sequence<String>): Int {
        val burrow = Burrow(lines.toList())

        val search = dijkstraSearch(
            init = BurrowState(burrow,null,burrow.rows),
            found = { state -> state.isFinish },
            children = { state -> state.next() },
            cost = { state -> 100 }
        )

        return search.first().costs

    }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }
}