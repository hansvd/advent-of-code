import Day25.Cucumber.Companion.createFrom

object Day25 {
    data class Cucumber(val x: Int, val y: Int, val direction:Char) {

        fun nextX(width:Int) = if (x+1 >= width) 0 else x+1
        fun nextY(heigth:Int) = if (y+1 >= heigth) 0 else y+1
        val isEast = direction == '>'
        val isSouth = direction == 'v'
        override fun toString(): String = "($x,$y:$direction)"
        companion object {
            fun createFrom(x:Int, y:Int, c:Char):Cucumber? {
                if (c!= '>' && c != 'v') return null
                return Cucumber(x,y,c)
            }
        }
    }
    data class SeeBottom(val cucumbers:Set<Cucumber>, val width:Int, val height:Int) {


        fun step():SeeBottom {
            val east = cucumbers.map { if (it.isEast && cucumbers.none { other -> other.x == it.nextX(width) && it.y == other.y})
                it.copy(x=it.nextX(width)) else it  }.toSet()
            val south= east.map { if (it.isSouth && east.none { other -> other.y == it.nextY(height) && it.x == other.x})
                it.copy(y=it.nextY(height)) else it  }.toSet()
            return copy(cucumbers = south)
        }

        override fun toString(): String {
            val sb = StringBuilder()
            for (y in 0 until height) {
                if (y > 0)  sb.append('\n')
                for (x in 0 until width) {
                    val c = cucumbers.firstOrNull { it.x == x && it.y == y }
                    if (c == null) sb.append('.') else sb.append(c.direction)
                }
            }
            return sb.toString()
        }

        fun maxSteps(): Int {
            var i = 0
            var cur = this
            do {
                var newCur = cur.step()
                i++
                if (cur == newCur) return i
                cur = newCur
            } while(true)
        }
    }
    fun part1(lines: Sequence<String>): Int {
        return 0
    }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }

    fun parseInput(lines:List<String>):SeeBottom = SeeBottom(lines.mapIndexed { y, row ->
        row.mapIndexedNotNull { x, c ->  createFrom(x,y,c) } }.flatten().toSet(), lines[0].length,lines.size)

}