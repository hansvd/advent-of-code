package aoc2023.day06

object Day {

    data class TimeDistance(val time:Long, val distance: Long)
    fun part1(lines: List<String>):Long {
        return parseInput(lines,false).map { td ->
            (0L..td.time).filter { (td.time-it) * it > td.distance }.size.toLong()
        }.reduce {acc, d -> acc * d}
    }

    fun part2(lines: List<String>) = parseInput(lines,true).map { td ->
        td.distance / d
    }.reduce {acc, d -> acc * d}

    fun parseInput(lines:List<String>, part2:Boolean):List<TimeDistance> {
        val l0 = if (part2) lines[0].replace(" ","", ) else lines[0]
        val l1 = if (part2) lines[1].replace(" ","") else lines[1]

        val t= l0.split(':')[1].trim().split(' ').filter { it.isNotBlank() }
        val d = l1.split(':')[1].trim().split(' ').filter { it.isNotBlank() }
        return t.indices.map { TimeDistance(t[it].toLong(),d[it].toLong()) }
    }
}