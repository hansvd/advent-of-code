object Day23 {
    data class Point(val x: Int, val y: Int) {
        val above get() = Point(x, y - 1)
        val below get() = Point(x, y + 1)
        val left get() = Point(x-1,y)
        val right get() = Point(x+1,y)

        override fun toString(): String {
            return "($x,$y)"
        }
    }

    fun List<String>.getFreeSpaces(): List<Point> =
        this.mapIndexed { r, s -> s.mapIndexedNotNull { c, ch -> if (ch == '.') Point(c, r) else null } }.flatten()

    fun List<String>.getAmphipods(): List<Point> =
        this.mapIndexed { r, s -> s.mapIndexedNotNull { c, ch -> if (ch in 'A'..'D') Point(c, r) else null } }.flatten()

    fun List<String>.print() {
        println("--")
        for (r in this) println(r)
    }

    class Burrow(val rows: List<String>) {
        val hallway =  (1..11).map { Point(it,1)}
        val sideRooms = listOf(3,5,7,9).map { x-> (2..rows.size-2).map { y-> Point(x,y)} }.flatten()
        fun isSideRoom(p: Point):Boolean = sideRooms.contains(p)
        fun isHallway(p: Point) = hallway.contains(p)
        fun isSideRoomFor(p2: Point, amphipod: Char): Boolean {
            if (!isSideRoom(p2)) return false
            if (p2.x==3 && amphipod != 'A') return false
            if (p2.x==5 && amphipod != 'B') return false
            if (p2.x==7 && amphipod != 'C') return false
            if (p2.x==9 && amphipod != 'D') return false
            return true
        }

        fun sideRoomXFor(amphipod: Char): Int {
            when (amphipod) {
                'A' -> return 3
                'B' -> return 5
                'C' -> return 7
                'D' -> return 9
            }
            return 0
        }

    }

    data class BurrowState(val burrow: Burrow, val previous: BurrowState?, val rows: List<String>, val costs: Int = 0) {
        override fun equals(other: Any?): Boolean = (other as? BurrowState)?.hashCode() == hashCode()
        override fun hashCode(): Int = rows.joinToString().hashCode() + costs.hashCode()

        val totalCosts: Int =  costs
        fun contains(state: BurrowState): Boolean {
            if (previous == null) return false
            if (previous == state) return true
            return previous.contains(state)
        }

        private fun content(p: Point) = content(p.x, p.y)
        private fun content(x: Int, y: Int): Char {
            if (x < 0 || y < 0 || y >= rows.size) return '#'
            val r = rows[y]
            if (x >= r.length) return '#'
            return r[x]
        }

        private fun move(p1: Point, p2: Point): BurrowState {
            val amphipod = content(p1)
            val newRows = rows.mapIndexed { y, row ->
                if (y != p2.y) row else row.substring(
                    0,
                    p2.x
                ) + amphipod + row.substring(p2.x + 1)
            }
                .mapIndexed { y, row -> if (y != p1.y) row else row.substring(0, p1.x) + '.' + row.substring(p1.x + 1) }

            val c = if (amphipod == 'A') 1 else if (amphipod == 'B') 10 else if (amphipod == 'C') 100 else 1000
            val steps = stepsNeeded(p1, p2, rows.getFreeSpaces())
            return this.copy(rows = newRows, previous = this, costs = c*steps + costs)
        }

        private fun isValidMove(p1: Point, p2: Point): Boolean {
            val amphipod = content(p1)

            // only move from hallway into sideRoom
            if (burrow.isHallway(p1) && burrow.isHallway(p2))
                return false

            if (burrow.isSideRoomFor(p1,amphipod)) {
                if ((p1.y + 1 .. rows.size-2).all { y-> content(p1.x, y) == amphipod}) return false // it's already in place
            }
            // only move from the hallway into the dest room
            if (burrow.isSideRoom(p2)) {
                if (!burrow.isSideRoomFor(p2,amphipod)) return false
                if ((p2.y + 1 .. rows.size-2).any { y -> rows.getFreeSpaces().contains(Point(p2.x,y)) }) return false
                if (burrow.isSideRoom(p2.below)) {
                    if ((p2.y + 1 .. rows.size-2).any { y -> content(p2.x,y) != amphipod }) return false
                }
            }
            //Amphipods will never stop on the space immediately outside any room
            if (burrow.isSideRoom(p2.below) && burrow.isHallway(p2)) return false

            // anything on the rood
            val freeSpaces = rows.getFreeSpaces()
            if (stepsNeeded(p1, p2, freeSpaces) < 0) return false

            // is there a more efficient path
            val x = burrow.sideRoomXFor(amphipod)
            if (p2.x != x && freeSpaces.contains(Point(x,2))
                && (2..rows.size-2).all {y -> content(x,y) == '.' || content(x,y) == amphipod }
                && stepsNeeded(p1,Point(x,2),freeSpaces) >= 0) return false

            return true
        }

        private fun stepsNeeded(p1: Point, p2: Point, freeSpaces: List<Point>, stepsDone:Int = 0): Int {
            if (p1 == p2) return stepsDone

            var steps = -1
            if (freeSpaces.contains(p1.above))
                steps = stepsNeeded(p1.above, p2, freeSpaces - p1.above,stepsDone+1)
            if (steps >= 0) return  steps
            if (freeSpaces.contains(p1.below))
                steps = stepsNeeded(p1.below, p2, freeSpaces - p1.below,stepsDone+1)
            if (steps >= 0) return  steps
            if (freeSpaces.contains(p1.left))
                steps = stepsNeeded(p1.left, p2, freeSpaces - p1.left,stepsDone+1)
            if (steps >= 0) return  steps
            if (freeSpaces.contains(p1.right))
                steps = stepsNeeded(p1.right, p2, freeSpaces - p1.right,stepsDone+1)
            if (steps >= 0) return  steps
            return steps
        }

        val isFinish: Boolean by lazy {
            burrow.sideRooms.all { s -> this.content(s) in 'A'..'D' }
                    && burrow.sideRooms.all { s -> content(s) == content(s.above) || content(s) == content(s.below) }
        }
        fun printAll() {
            previous?.printAll()
            rows.print()
        }
        fun next(): List<BurrowState> {
            val amphipods = rows.getAmphipods()
            val freeSpaces = rows.getFreeSpaces()
            return amphipods.map { a -> freeSpaces.filter { f -> isValidMove(a, f) }.map { f -> move(a, f) } }.flatten()
        }

    }


    fun invoke(lines: List<String>): Int {
        val burrow = Burrow(lines.toList())
        val init = BurrowState(burrow, null, burrow.rows)
        if (init.isFinish) return 0

        val search = dijkstraSearch(
            init = BurrowState(burrow, null, burrow.rows),
            found = { state -> state.isFinish },
            children = { state -> state.next() },
            cost = { state -> state.costs }
        )

        val f1 = search
        val f = f1.minByOrNull { it.totalCosts }
        f?.printAll()
        return f?.totalCosts ?: 0
    }
}