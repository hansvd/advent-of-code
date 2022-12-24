package aoc2022.day20

object Day202220 {

    fun part1(lines: Sequence<String>) = decrypt(lines.map { it.toInt() })

    fun part2(lines: Sequence<String>) = decrypt(lines.map { it.toInt() }, 811589153, 10)

    private fun decrypt(input: Sequence<Int>, multiplier: Long = 1, repeat: Int = 1): Long {
        val result = input.mapIndexed { index, i -> Pair(index, i * multiplier) }.toMutableList()
        repeat(repeat) { mix(result) }
        val i = result.indexOfFirst { it.second == 0L }
        return (1000..3000 step (1000)).sumOf { result[(i + it) % result.size].second }
    }

    private fun mix(result: MutableList<Pair<Int, Long>>) {
        (0 until result.size).forEach { i ->
            move(result, i)
        }
    }

    fun move(list: MutableList<Pair<Int, Long>>, i: Int) {
        val mod = (list.size - 1).toLong()
        val currentIndex = list.indexOfFirst { it.first == i }
        val value = list[currentIndex]
        val newIndex = (value.second + currentIndex).mod(mod).toInt()
        if (newIndex == currentIndex) return

        list.removeAt(currentIndex)
        list.add(newIndex, value)
    }

}