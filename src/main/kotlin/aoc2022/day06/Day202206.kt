package aoc2022.day06

object Day202206 {

    fun part1(line: String): Int = findForSize(line.toCharArray())

    fun part2(line: String): Int = findForSize(line.toCharArray(), 14)

    private fun findForSize(input: CharArray, n: Int = 4): Int =
        (n until input.size).firstOrNull {
            input.slice(it - n until it)
                .distinct().size == n
        } ?: 0
}