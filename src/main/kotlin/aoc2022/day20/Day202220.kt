package aoc2022.day20

object Day202220 {

    fun order(input: Sequence<Int>):Int {
        val result = input.mapIndexed { index, i -> Pair(index, i) }.toMutableList()

        (0 until result.size).forEach { i ->
            move(result, i)
            if (result.size < 10) println(result.map { it.second })
        }
        val i = result.indexOfFirst { it.second == 0 }
        val v1 = result[(i+1000) % result.size].second
        val v2 = result[(i+2000) % result.size].second
        val v3 = result[(i+3000) % result.size].second

        return v1+v2+v3
    }

    fun move(list: MutableList<Pair<Int, Int>>, i: Int) {
        val mod = list.size-1
        val currentIndex = list.indexOfFirst { it.first == i }
        val value = list[currentIndex]
        val newIndex = (currentIndex + value.second).mod(mod)
        if (newIndex == currentIndex) return

        list.removeAt(currentIndex)
        list.add(newIndex, value)
    }

    fun part1(lines: Sequence<String>): Int {
        return order(lines.map { it.toInt() })
    }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }
}