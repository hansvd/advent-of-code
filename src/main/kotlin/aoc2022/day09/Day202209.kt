package aoc2022.day09

object Day202209 {

    data class Point(val x: Int, val y: Int) {
        val up get() = Point(x, y + 1)
        val down get() = Point(x, y - 1)
        val left get() = Point(x - 1, y)
        val right get() = Point(x + 1, y)

        override fun toString(): String = "($x,$y)"
    }

    class Grid(tailNb: Int = 2) {
        private var knots = MutableList(tailNb) { Point(0, 0) }
        private val trail = mutableListOf(Point(0, 0))

        val head get() = knots.first()
        val tail get() = knots.last()
        fun handleInput(lines: Sequence<String>): Int {
            lines.forEach { l ->
                val c = l[0]
                val nb = Integer.valueOf(l.substringAfter(" "))

                repeat(nb) {
                    step(c)
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

            for (i in 1 until knots.size) {
                knots[i] = tailStep(knots[i - 1], knots[i])
            }

            if (trail.last() != tail)
                trail.add(tail)
        }


        companion object {
            private fun tailStep(head: Point, tailIn: Point): Point {
                var tail = tailIn
                if (head.x - tail.x > 1) {
                    tail = tail.right
                    tail = connectY(head, tail)
                }

                if (tail.x - head.x > 1) {
                    tail = tail.left
                    tail = connectY(head, tail)
                }

                if (head.y - tail.y > 1) {
                    tail = tail.up
                    tail = connectX(head, tail)
                }

                if (tail.y - head.y > 1) {
                    tail = tail.down
                    tail = connectX(head, tail)
                }
                return tail
            }

            private fun connectY(head: Point, tail: Point): Point {
                if (head.y - tail.y > 0)
                    return tail.up

                if (tail.y - head.y > 0)
                    return tail.down
                return tail
            }

            private fun connectX(head: Point, tail: Point): Point {
                if (head.x - tail.x > 0)
                    return tail.right

                if (tail.x - head.x > 0)
                    return tail.left
                return tail
            }
        }

    }

    fun part1(lines: Sequence<String>): Int {
        val grid = Grid()
        return grid.handleInput(lines)
    }


    fun part2(lines: Sequence<String>): Int {
        val grid = Grid(10)
        return grid.handleInput(lines)
    }
}