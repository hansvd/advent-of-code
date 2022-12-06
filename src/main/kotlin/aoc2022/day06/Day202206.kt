package aoc2022.day06

object Day202206 {

    fun part1(line:String): Int {
        for (i in 4 until line.length)
            if (line.toCharArray().slice(i-4 until i).distinct().size == 4)
                return i
        return 0
    }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }
}