package aoc2022.day08

object Day202208 {

    class Grid(private val rows: List<MutableList<Int>>) {
        val h = rows.size
        val w = rows[0].size

        private fun isVisible(r: Int, c: Int): Boolean {
            val i = rows[r][c]
            return (0 until c).all { rows[r][it] < i }
                    || (c + 1 until w).all { rows[r][it] < i }
                    || (0 until r).all { rows[it][c] < i }
                    || (r + 1 until h).all { rows[it][c] < i }
        }

        private fun score(r: Int, c: Int): Int {
            val i = rows[r][c]
            val s1 = c - ((c - 1 downTo 0).firstOrNull { rows[r][it] >= i } ?: 0)
            val s2 = ((c + 1 until w).firstOrNull { rows[r][it] >= i } ?: (w - 1)) - c
            val s3 = r - ((r - 1 downTo 0).firstOrNull { rows[it][c] >= i } ?: 0)
            val s4 = ((r + 1 until h).firstOrNull { rows[it][c] >= i } ?: (h - 1)) - r
            return s1 * s2 * s3 * s4
        }

        fun maxScore(): Int = (1 until h - 1).maxOf { r ->
            (1 until w - 1).maxOf { c -> score(r, c) }
        }

        fun countVisible(): Int = (rows.indices).sumOf { r ->
            (0 until rows[r].size).count { c -> isVisible(r, c) }
        }
    }

    fun part1(lines: Sequence<String>): Int = readInput(lines).countVisible()

    fun part2(lines: Sequence<String>): Int = readInput(lines).maxScore()

    private fun readInput(lines: Sequence<String>): Grid = Grid(lines.map { line ->
        line.map { it.code - '0'.code }.toMutableList()
    }.toList())
}