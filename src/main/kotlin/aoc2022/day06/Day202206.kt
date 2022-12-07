package aoc2022.day06

object Day202206 {

    fun part1(line: String): Int = findForSize(line)

    fun part2(line: String): Int = findForSize(line, 14)

    private fun findForSize(input: String, n: Int = 4): Int =
        input.windowedSequence(n,1).indexOfFirst {
            it.toSet().size == n
        } + n
}