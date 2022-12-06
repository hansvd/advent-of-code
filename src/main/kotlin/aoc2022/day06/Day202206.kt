package aoc2022.day06

object Day202206 {

    fun part1(line: String): Int = findForSize(line.toList())

    fun part2(line: String): Int = findForSize(line.toList(), 14)

    private fun findForSize(input: List<Char>, n: Int = 4): Int =
        input.windowed(n,1).indexOfFirst {
            it.distinct().size == n
        } + n
}