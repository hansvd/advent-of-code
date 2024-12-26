package aoc2024.day03

object Day {

    fun part1(lines: List<String>): Int = parseInput(lines).sumOf { it.first * it.second }

    fun part2(line: String): Int = parseInput2(line).sumOf { it.first * it.second }

    private val regEx = Regex("""mul\((\d+),(\d+)\)""")
    fun parseInput(lines: List<String>): List<Pair<Int, Int>> =
        lines.flatMap {
            this.regEx.findAll(it).map {
                it.groupValues[1].toInt() to it.groupValues[2].toInt()
            }
        }

    fun parseInput2(line: String): List<Pair<Int, Int>> =
        line.split("do()").flatMap {
            val s = it.split("don't()")
            this.regEx.findAll(s.first()).map {
                it.groupValues[1].toInt() to it.groupValues[2].toInt()
            }
        }
}