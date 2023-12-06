package aoc2023.day06

object Day {

    data class TimeDistance(val time:Int, val distance: Int)
    fun part1(lines: List<String>):Int {
        return parseInput(lines).map { td ->
            (0..td.time).filter { (td.time-it) * it > td.distance }.size
        }.reduce {acc, d -> acc * d}
    }

    fun part2(lines: List<String>): Int = 0

    fun parseInput(lines:List<String>):List<TimeDistance> {
        val t= lines[0].split(':')[1].trim().split(' ').filter { it.isNotBlank() }
        val d = lines[1].split(':')[1].trim().split(' ').filter { it.isNotBlank() }
        return t.indices.map { TimeDistance(t[it].toInt(),d[it].toInt()) }
    }
}