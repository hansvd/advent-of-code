package aoc2023.day06

object Day {

    data class TimeDistance(val time:Long, val distance: Long)
    fun part1(lines: List<String>) =  solve(parseInput(lines, false))


    fun part2(lines: List<String>) = solve(parseInput(lines, true))

    private fun solve(tds: List<TimeDistance>) = tds.map { td ->
            // find first one
            val f = (1L until td.time).first { (td.time - it) * it > td.distance }
            val l = (td.time -1 downTo 0L).first { (td.time - it) * it > td.distance }
            l - f + 1
        }.reduce { acc, d -> acc * d }

    fun parseInput(lines:List<String>, part2:Boolean):List<TimeDistance> {
        val l0 = if (part2) lines[0].replace(" ","", ) else lines[0]
        val l1 = if (part2) lines[1].replace(" ","") else lines[1]

        val t= l0.split(':')[1].trim().split(' ').filter { it.isNotBlank() }
        val d = l1.split(':')[1].trim().split(' ').filter { it.isNotBlank() }
        return t.indices.map { TimeDistance(t[it].toLong(),d[it].toLong()) }
    }
}