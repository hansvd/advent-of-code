

object Day17 {
    fun go(x:IntRange, y:IntRange, v:Velocity): Point? {
        return Trench(x,y).go(Point(0,0),v).lastOrNull()
    }

    fun part1(x:IntRange, y:IntRange): Int? {
        val r = Trench(x,y).findVelocity().toList()

        return r.maxByOrNull { it.second.maxByOrNull { it.y }?.y ?: 0 }?.second?.maxByOrNull { it.y }?.y
    }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }

    data class Point(val x:Int, val y:Int)
    data class Velocity(val x:Int, val y:Int) {
        fun next():Velocity = Velocity(if (x > 0) x-1 else if (x < 0) x+1 else 0, y-1)
    }
    class Trench(val targetX:IntRange, val targetY:IntRange) {

        fun findVelocity():Sequence<Pair<Velocity,List<Point>>> {
            return sequence {
                for (x in 1..targetX.endInclusive) {
                    for (y in targetY.endInclusive ..-targetY.start) {
                        val s = go(Point(0, 0), Velocity(x, y))
                        if (s.isNotEmpty()) yield(Pair(Velocity(x, y),s))
                    }
                }
            }
        }
        fun go (start:Point = Point(0,0), velocity: Velocity) :List<Point>
            = go (listOf(start),velocity)
        fun go(cur:List<Point>, velocity:Velocity):List<Point> {
            val start = cur.last()
            if (start.x in targetX && start.y in targetY) return cur
            if (start.x > targetX.endInclusive || start.y < targetY.endInclusive) return listOf()
            return go(cur + Point(start.x + velocity.x,start.y+velocity.y), velocity.next())
        }
    }

}