package aoc2022.day16

import shared.dijkstraSearch

object Day202216 {

    data class Valve(val name: String, val rate: Int, val tunnelsTo: List<String>)

    data class Step(val valve: Valve, val score: Int, val timeLeft: Int, val previous: Step?) {
        val name = valve.name
        override fun equals(other: Any?): Boolean = (other as? Step)?.name == name
        override fun hashCode(): Int = name.hashCode()

        val stepNb: Int = (previous?.stepNb ?: -1) + 1
    }
    class Result(val v:Valve, val steps:List<Step>, val score:Int, val timeLeft: Int)
    class Cave(val valves: Map<String, Valve>) {

        fun invoke(): Int {
            val start = valves["AA"]!!
            val results = findRecur(start, listOf(start),30)
            val r = results.maxBy { it.score }
            return r.score
        }
        fun findRecur(start:Valve, openValves:List<Valve>, timeLeft: Int):List<Result> {
            if (timeLeft < 2) return listOf() // no time to open another valve
            val ends = valves.values.filter{it.rate > 0 && !openValves.contains(it)}.sortedByDescending { it.rate }

            val results = ends.mapNotNull { findFromTo(start,it,timeLeft) }.sortedByDescending { it.score }
            val endResults = mutableListOf<Result>()
            for (r in results) {
                val result2 = findRecur(r.v, openValves + r.v, r.timeLeft)
                if (result2.isEmpty())
                    endResults.add(r)
                else
                    result2.forEach {
                        endResults.add(Result(it.v, r.steps + it.steps, it.score + r.score, it.timeLeft)) }
            }
            return endResults
        }

        fun findFromTo(start:Valve, end:Valve, timeLeft:Int):Result? {
            val r = dijkstraSearch(
                init = Step(start, 0,timeLeft, null),
                found = { step -> step.timeLeft <= 1 || step.valve.name == end.name },
                children = { step -> step.valve.tunnelsTo.map { Step(valves[it]!!,step.score,step.timeLeft-1, step) } },
                cost = { it.stepNb }
            ).firstOrNull() ?: return null

            if (r.name != end.name) return null
            return Result( end,listOf(r),(r.timeLeft-1) * r.valve.rate, r.timeLeft -1 )
        }
    }


    fun part1(lines: Sequence<String>): Int {
        return parseInput(lines).invoke()
    }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }

    private val reg = """Valve (..) has flow rate=(-?\d+); tunnel(s)? lead(s)? to valve(s)? (.+)""".toRegex()
    private fun parseInput(lines: Sequence<String>) = Cave(lines.mapNotNull { l ->
        val match = reg.matchEntire(l)
        if (match != null)
            match.groupValues[1] to Valve(
                match.groupValues[1],
                match.groupValues[2].toInt(),
                match.groupValues[6].split(", ")
            )
        else null
    }.toMap())
}