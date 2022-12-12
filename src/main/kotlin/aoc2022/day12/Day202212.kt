package aoc2022.day12

import shared.dijkstraSearch

object Day202212 {

    data class Point(val x: Int, val y: Int) {
        fun adjacent(width:Int, height:Int):List<Point> =
            listOf(Point(x-1,y),Point(x+1,y), Point(x,y-1), Point(x,y+1))
                .filter { it.x in 0 until width && it.y in 0 until height }
    }
    data class Step(val pos: Point, val previous: Step?) {
        override fun equals(other: Any?): Boolean = (other as? Step)?.pos == pos
        override fun hashCode(): Int = pos.hashCode()
        val stepNb: Int = (previous?.stepNb ?: -1) + 1
    }

    class Area(val map:List<List<Char>>) {
        private val height = map.size
        private val width = map[0].size
        private val end = find('E')
        val start = find('S')
        val possibleStarts = listOf(start)  +
            (0 until height).map { y -> (0 until width).filter { map[y][it] == 'a' }. map { Point(it,y) } }
                .flatten()

        fun get(p:Point):Char = map[p.y][p.x].let { if (it == 'S') 'a' else if (it == 'E') 'z' else it }
        private fun find(c:Char):Point = (0 until height).map { y ->
            (0 until width).filter { map[y][it] == c }.map { Point(it,y) }
        }.flatten().first()

        fun next(pos:Point):List<Point> = get(pos).let { c -> pos.adjacent(width, height).filter { get(it) <= c + 1 } }

        fun invoke(start:Point):Int? {
           val r = dijkstraSearch(
                init = Step(start, null),
                found = { step -> step.pos == end },
                children = { step -> next(step.pos).map { neighbor -> Step(neighbor, step) } },
                cost = { it.stepNb }
            )
            return r.minOfOrNull { it.stepNb }
        }
    }
    fun part1(lines: Sequence<String>): Int {
        val area = parseInput(lines)
        return area.invoke(area.start) ?: 0
    }

    fun part2(lines: Sequence<String>): Int {
        val area = parseInput(lines)
        return area.possibleStarts.mapNotNull { area.invoke(it)}.minOf { it }
    }

    fun parseInput(lines:Sequence<String>) : Area {
        return Area(lines.map { it.toList()}.toList())
    }
}