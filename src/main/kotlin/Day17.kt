

object Day17 {
    fun go(x:IntRange, y:IntRange, v:Velocity): Point? = Trench(x,y).go(Point(0,0),v).lastOrNull()

    fun part1(x:IntRange, y:IntRange): Int
        = Trench(x,y).findVelocity().maxOf { r -> r.second.maxOf { it.y }  }

    fun part2(x:IntRange, y:IntRange): Int = Trench(x,y).findVelocity().count()

    data class Point(val x:Int, val y:Int) {
        fun move(v:Velocity):Point = Point(x + v.x,y+v.y)
    }
    data class Velocity(val x:Int, val y:Int) {
        fun next():Velocity = Velocity(if (x > 0) x-1 else if (x < 0) x+1 else 0, y-1)
    }
    class Trench(private val targetX:IntRange, private val targetY:IntRange) {

        fun findVelocity():Sequence<Pair<Velocity,List<Point>>> {
            return sequence {
                for (x in 1..targetX.last) {
                    for (y in targetY.first..-targetY.first) {
                        val s = go(Point(0, 0), Velocity(x, y))
                        if (s.isNotEmpty()) yield(Pair(Velocity(x, y),s))
                    }
                }
            }
        }
        fun go (start:Point = Point(0,0), velocity: Velocity) :List<Point>
            = go (listOf(start),velocity)
        private fun go(cur:List<Point>, velocity:Velocity):List<Point> {
            val last = cur.last()
            if (hit(last)) return cur
            if (toFar(last)) return listOf()
            return go(cur + last.move(velocity), velocity.next())
        }
        private fun hit(p:Point) = (p.x in targetX && p.y in targetY)
        private fun toFar(p:Point) = (p.x > targetX.last || p.y < targetY.first)
    }

}