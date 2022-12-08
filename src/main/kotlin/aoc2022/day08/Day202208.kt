package aoc2022.day08

object Day202208 {

    class Grid(private val rows: List<MutableList<Int>>) {
        val h = rows.size
        val w = rows[0].size

        private fun isVisible(r: Int, c: Int): Boolean =
                (0 until c).all { rows[r][it] < rows[r][c] }
                || (c + 1 until w).all { rows[r][it] < rows[r][c] }
                || (0 until r).all { rows[it][c] < rows[r][c] }
                || (r + 1 until h).all { rows[it][c] < rows[r][c] }

        private fun score(r: Int, c: Int): Int =
                (c - 1 downTo 0).takeWhile { rows[r][it] >= rows[r][c] }.size *
                (c + 1 until w).takeWhile { rows[r][it] >= rows[r][c] }.size  *
                (r - 1 downTo 0).takeWhile { rows[it][c] >= rows[r][c] }.size *
                (r + 1 until h).takeWhile { rows[it][c] >= rows[r][c] }.size

        fun maxScore(): Int = (1 until h - 1).maxOf { r ->
            (1 until w - 1).maxOf { c -> score(r, c) }
        }

        fun countVisible(): Int = (rows.indices).sumOf { r ->
            (0 until rows[r].size).count { c -> isVisible(r, c) }
        }
    }

    fun part1(lines: Sequence<String>): Int {
        return readInput(lines).countVisible()
    }

    fun part2(lines: Sequence<String>): Int {
        return readInput(lines).maxScore()
    }

    private fun readInput(lines: Sequence<String>): Grid = Grid(lines.map { line ->
        line.map { it.code - '0'.code }.toMutableList()
    }.toList())
}