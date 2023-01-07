package aoc2022.day24

import shared.Point
import shared.containsNo
import java.util.*

object Day202224 {

    class Board(private val rows: List<List<Char>>) {
        //val height = rows.size
        private val width = rows[0].size
        private val height = rows.size

        private val start = Point(rows[0].indexOf('.'), 0)
        private val end = Point(rows[rows.lastIndex].lastIndexOf('.'), rows.lastIndex)

        private fun isFreeAt(p: Point, time: Int) =
            if ((0 until width).containsNo(p.x) || (0 until height).containsNo(p.y)) false
            else if (p.x == 0 || p.y == 0 || p.y == height - 1 || p.x == width - 1) {
                get(p) == '.' // border is timeless
            } else {
                get(movingPointAt(p.x, time, width), p.y) != '<'
                        && get(movingPointAt(p.x, -time, width), p.y) != '>'
                        && get(p.x, movingPointAt(p.y, time, height)) != '^'
                        && get(p.x, movingPointAt(p.y, -time, height)) != 'v'
            }

        // blizzards move (backward or forward) from 1 .. size
        private fun movingPointAt(p: Int, time: Int, size: Int): Int = (p - 1 + time).mod(size - 2) + 1

        data class State(val time: Int, val pos: Point)

        fun startToEnd(startTime: Int = 0) = go(start, end, startTime)
        fun endToStart(startTime: Int) = go(end, start, startTime)

        private fun go(start: Point, end: Point, startTime: Int): Int {
            val done = mutableSetOf(State(startTime, start))
            val queue = PriorityQueue(compareBy(IndexedValue<State>::index))
            queue.add(IndexedValue(0, State(startTime, start)))
            while (!queue.isEmpty()) {
                val entry = queue.remove().value
                val (time, pos) = entry
                if (pos == end) return time

                pos.adjacentWithinManhattanDistance(1).forEach { p2 ->
                    if (!isFreeAt(p2, time + 1)) return@forEach
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

    fun part1(lines: Sequence<String>) = parseInput(lines).startToEnd()

    fun part2(lines: Sequence<String>): Int = parseInput(lines).run { startToEnd(endToStart(startToEnd())) }

    fun parseInput(lines: Sequence<String>) = Board(lines.map { row -> row.map { it } }.toList())
}