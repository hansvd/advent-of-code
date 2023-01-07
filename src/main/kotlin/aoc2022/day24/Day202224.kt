package aoc2022.day24

import shared.Point
import java.util.*

object Day202224 {

    class Board(private val rows: List<List<Char>>) {
        //val height = rows.size
        private val width = rows[0].size
        private val height = rows.size

        private val start = Point(rows[0].indexOf('.'), 0)
        private val end = Point(rows[rows.lastIndex].lastIndexOf('.'), rows.lastIndex)

        private fun isFreeAt(p: Point, time: Int) =
            if (p.x < 0 || p.y < 0) false
            else if (p.x == 0 || p.y == 0 || p.y == height - 1 || p.x == width - 1) {
                get(p) == '.' // border is timeless
            } else {
                get(((p.x - 1 + time).mod(width - 2)) + 1, p.y) != '<' &&
                        get(((p.x - 1 - time).mod(width - 2)) + 1, p.y) != '>' &&
                        get(p.x, ((p.y - 1 + time).mod(height - 2)) + 1) != '^' &&
                        get(p.x, ((p.y - 1 - time).mod(height - 2)) + 1) != 'v'

            }
        data class State(val time:Int, val pos:Point)

        fun invoke(startTime: Int = 0): Int {
            val done = mutableSetOf(State(startTime, start))
            val queue = PriorityQueue(compareBy(IndexedValue<State>::index))
            queue.add(IndexedValue(0, State(startTime, start)))
            while (!queue.isEmpty()) {
                val entry = queue.remove().value
                val (time, pos) = entry
                if (pos == end) return time

                for (p2 in pos.adjacentWithinManhattanDistance(1)) {
                    if (!isFreeAt(p2, time + 1)) continue
                    val state = State(time + 1, p2)
                    if (done.add(state))
                        queue.add(IndexedValue(time + p2.manhattanDistance(end), state))
                }
            }
            throw NoSuchElementException()
        }

        private fun get(p: Point) = rows[p.y][p.x]
        private fun get(x: Int, y: Int) = rows[y][x]
    }

    fun part1(lines: Sequence<String>) = parseInput(lines).invoke()

    fun part2(lines: Sequence<String>): Int {
        return 0
    }

    fun parseInput(lines: Sequence<String>) = Board(lines.map { row ->
        row.map { it }
    }.toList())
}