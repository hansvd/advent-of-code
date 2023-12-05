package aoc2023.day05

object Day {

    /*
    seed-to-soil map:
50 98 2
52 50 48
     */
    data class Range(val desStart:Long, val srcStart: Long, val length:Long) {
        val src = srcStart..(srcStart+length)
        //val dst = desStart.. (desStart+length)
    }
    data class FromToMap(val from:String, val to:String, val ranges:List<Range>) {

        fun map(from:Long) = ranges.firstOrNull { from in it.src }?.let {
                it.desStart + (from - it.srcStart )
            } ?: from
        }

    fun part1(lines: List<String>):Long {
        val (seeds, maps) = parseInput(lines)
        return seeds.minOfOrNull { maps.fold(it) { acc, m -> m.map(acc) } } ?: 0L
    }

    fun part2(lines: List<String>): Int = 0

    fun parseInput(lines:List<String>):Pair<List<Long>,List<FromToMap>> {
        val seeds = lines[0].split(':')[1].trim().split(' ').map {it.toLong()}

        var s = 2
        val maps = mutableListOf<FromToMap>()
        for (i in 2 until lines.size) {
            if (lines[i].isBlank()) {
                maps.add(parseMap(lines.subList(s,i)))
                s = i+ 1
            }
        }
        maps.add(parseMap(lines.subList(s,lines.size)))
        return seeds to maps
    }

    private fun parseMap(lines: List<String>): FromToMap {
        val fromTo = lines[0].split(' ')[0].split("-to-")
        return FromToMap(fromTo[0], fromTo[1],
            lines.drop(1).map {
                val r = it.split(' ').map { it.toLong() }
                Range(r[0], r[1], r[2])
            })
    }

}