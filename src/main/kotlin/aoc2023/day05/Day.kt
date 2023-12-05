package aoc2023.day05

import shared.intersect

object Day {

    data class Range(val desStart:Long, val srcStart: Long, val length:Long) {
        val src = srcStart..(srcStart+length)

        data class MapResult(val todo:List<LongRange>, val done:List<LongRange>)

        fun map(r:LongRange):MapResult {
            val i = src.intersect(r)
            if (i.isEmpty()) return MapResult(listOf(r), listOf())

            val done = listOf(
                desStart + (i.first - srcStart) ..desStart + (i.last - srcStart)
            )
            val todo = mutableListOf<LongRange>()
            if (r.first < src.first)  todo.add(r.first until src.first)
            if (src.last < r.last) todo.add(src.last + 1 .. r.last)
            return MapResult(todo, done)
        }
    }
    data class FromToMap(val from:String, val to:String, val ranges:List<Range>) {

        fun map(from:Long) = ranges.firstOrNull { from in it.src }?.let {
                it.desStart + (from - it.srcStart )
            } ?: from

        fun mapRanges(r:List<LongRange>):List<LongRange> = r.flatMap { mapRanges(it) }
        private fun mapRanges(r:LongRange):List<LongRange> {
            val done = mutableListOf<LongRange>()
            var todo = listOf(r)
            ranges.forEach { range ->
                val newTodo = mutableListOf<LongRange>()
                todo.forEach {
                    val s = range.map(it)
                    newTodo.addAll(s.todo)
                    done.addAll(s.done)
                }
                todo = newTodo
            }
            return todo + done
        }
    }


    fun part1(lines: List<String>):Long {
        val (seeds, maps) = parseInput(lines)
        return seeds.minOfOrNull { maps.fold(it) { acc, m -> m.map(acc) } } ?: 0L
    }

    fun part2(lines: List<String>):Long {
        val (seeds, maps) = parseInput(lines)
        val ranges =
            (seeds.indices step 2).map { seeds[it]..(seeds[it] + seeds[it+1]) }
        return ranges.minOfOrNull { r ->
            maps.fold(listOf(r)) { acc, m -> m.mapRanges(acc) }.minOfOrNull { it.first } ?: 0
        } ?: 0

    }


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
            lines.drop(1).map { l ->
                val r = l.split(' ').map { it.toLong() }
                Range(r[0], r[1], r[2])
            })
    }

}