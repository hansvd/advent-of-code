package aoc2022.day01

object Day202201 {

    private fun getSums(lines: List<String>): List<Int> {
        val sums = mutableListOf<Int>()
        sums.add(0)
        lines.forEach { l ->
            if (l.isBlank())
                sums.add(0)
            else
                sums[sums.size - 1] += l.toInt()
        }
        return sums
    }

    fun part1(lines: List<String>): Int = getSums(lines).max()

    fun part2(lines: List<String>): Int = getSums(lines).sortedDescending().take(3).sum()
}