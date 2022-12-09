package aoc2022.day09

import kotlin.math.abs
import kotlin.math.sign

object Day202209 {

    data class Point(val x: Int, val y: Int) {
        val up get() = Point(x, y + 1)
        val down get() = Point(x, y - 1)
        val left get() = Point(x - 1, y)
        val right get() = Point(x + 1, y)
        private fun isAdjacent(other:Point) = other != this && abs(this.x - other.x) <=1 && abs(y - other.y) <=1

        fun tailStep(tail: Point): Point =
            if (isAdjacent(tail)) tail
            else Point(tail.x + (x - tail.x).sign, tail.y + (y - tail.y).sign)

        override fun toString(): String = "($x,$y)"
    }

    class Grid(tailNb: Int = 2) {
        private var knots = MutableList(tailNb) { Point(0, 0) }
        private val trail = mutableListOf(Point(0, 0))

        val head get() = knots.first()
        val tail get() = knots.last()
        fun handleInput(lines: Sequence<String>): Int {
            lines.forEach { l ->
                repeat(Integer.valueOf(l.substringAfter(" "))) {
                    step(l[0])
                }
            }
            return trail.toSet().size
        }


        fun step(c: Char) {
            when (c) {
                'R' -> knots[0] = knots[0].right
                'L' -> knots[0] = knots[0].left
                'U' -> knots[0] = knots[0].up
                'D' -> knots[0] = knots[0].down
            }

            for (i in 1 until knots.size)
                knots[i] = knots[i - 1].tailStep(knots[i])

            if (trail.last() != tail)
                trail.add(tail)
        }
    }

    fun part1(lines: Sequence<String>): Int = Grid().handleInput(lines)

    fun part2(lines: Sequence<String>): Int = Grid(10).handleInput(lines)
}