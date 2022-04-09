package aoc2021
typealias Path = List<Day17.Point>
object Day17 {
    fun go(x:IntRange, y:IntRange, v: Velocity): Point? = Trench(x,y).go(Point(0,0),v).lastOrNull()

    fun part1(x:IntRange, y:IntRange): Int
        = Trench(x,y).findVelocities().maxOf { r -> r.second.maxOf { it.y }  }

    fun part2(x:IntRange, y:IntRange): Int = Trench(x,y).findVelocities().count()

    data class Point(val x:Int, val y:Int) {
        fun move(v: Velocity): Point = Point(x + v.dx,y+v.dy)
    }
    data class Velocity(val dx:Int, val dy:Int) {
        fun next(): Velocity = Velocity(if (dx > 0) dx-1 else if (dx < 0) dx+1 else 0, dy-1)
    }
    class Trench(private val targetX:IntRange, private val targetY:IntRange) {

        fun findVelocities():Sequence<Pair<Velocity, Path>> = sequence {
            (1..targetX.last).forEach { x->
                (targetY.first..-targetY.first).forEach { y->
                    go(Point(0, 0), Velocity(x, y)).let { path ->
                        if (path.isNotEmpty()) yield(Pair(Velocity(x, y), path))
                    }
                }
            }
        }
        fun go (start: Point = Point(0,0), velocity: Velocity) : Path
            = go (listOf(start),velocity)
        private fun go(cur: Path, velocity: Velocity): Path {
            val last = cur.last()
            if (isHit(last)) return cur
            if (isToFar(last)) return listOf()
            return go(cur + last.move(velocity), velocity.next())
        }
        private fun isHit(p: Point) = (p.x in targetX && p.y in targetY)
        private fun isToFar(p: Point) = (p.x > targetX.last || p.y < targetY.first)
    }

}