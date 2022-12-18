package aoc2022.day16

import shared.dijkstraSearch

object Day202216 {

    data class Valve(val name: String, val rate: Int, val tunnelsTo: List<String>) {
        override fun equals(other: Any?): Boolean = (other as? Step)?.name == name
        override fun hashCode(): Int = name.hashCode()
    }

    data class Step(val valve: Valve, val timeLeft: Int, val previous: Step?) {
        val name = valve.name
        override fun equals(other: Any?): Boolean = (other as? Step)?.name == name
        override fun hashCode(): Int = name.hashCode()

        val stepNb: Int = (previous?.stepNb ?: -1) + 1

        fun openValves():Set<Valve> = setOf(valve) + (previous?.openValves()?: setOf())

        val score:Int get() = (timeLeft - 1) * valve.rate

        override fun toString():String {
            return "$name:$score ($timeLeft)"
        }
    }
    class Result(val v:Valve, val steps:List<Step>, val openValves: Set<Valve>, val score:Int, val timeLeft: Int) {

        override fun toString():String {
            val sb = StringBuilder()
            for (i in steps) {
                sb.append(i.name + ":" +"$score (${i.timeLeft}) - ")
            }
            return sb.toString()
        }
    }
    class Cave(private val valves: Map<String, Valve>) {

        fun invoke(): Int {
            val start = valves["AA"]!!
            val results = findRecur(start, setOf(start),30)
            val r = results.maxBy { it.score }
            return r.score
        }

        fun invoke2(): Int {
            val start = valves["AA"]!!

            // take 100 is just a lucky shot which worked
            return sequence {
                findRecur(start, setOf(start),26).sortedByDescending { it.score }.take(100).forEach {
                    var score = 0
                    val openValves = mutableSetOf<Valve>()
                    for (i in 0 until it.steps.size){
                        val s = it.steps[i]
                        score += s.score
                        openValves += s.openValves()
                        yield(Result(s.valve, it.steps.take(i+1), openValves, score, s.timeLeft  ))
                    }
                }
            }.flatMap {r1 ->
                val result2 = findRecur(start, r1.openValves + start, 26)
                result2.map {Pair(r1,it)}
            }.maxOf { p ->
                        p.first.score + p.second.score}


        }
        private fun findRecur(start:Valve, openValves:Set<Valve>, timeLeft: Int):List<Result> {
            if (timeLeft < 2) return listOf() // no time to open another valve
            val ends = valves.values.filter{it.rate > 0 && !openValves.contains(it)}.sortedByDescending { it.rate }

            val results = ends.mapNotNull { findFromTo(start,it,openValves, timeLeft) }.sortedByDescending { it.score }
            val endResults = mutableListOf<Result>()
            for (r in results) {
                val result2 = findRecur(r.v, openValves + r.v, r.timeLeft)
                if (result2.isEmpty())
                    endResults.add(r)
                else
                    result2.forEach {
                        endResults.add(Result(it.v, r.steps + it.steps, it.openValves + r.openValves, it.score + r.score, it.timeLeft)) }
            }
            return endResults
        }


           private fun findFromTo(start:Valve, end:Valve, openValves: Set<Valve>, timeLeft:Int):Result? {
            val r = dijkstraSearch(
                init = Step(start,timeLeft, null),
                found = { step -> step.timeLeft <= 1 || step.valve.name == end.name },
                children = { step -> step.valve.tunnelsTo.map { Step(valves[it]!!,step.timeLeft-1, step) } },
                cost = { it.stepNb }
            ).firstOrNull() ?: return null

            if (r.name != end.name) return null
            return Result( end,listOf(r), openValves + end,r.score, r.timeLeft - 1 )
        }
    }


    fun part1(lines: Sequence<String>): Int {
        return parseInput(lines).invoke()
    }

    fun part2(lines: Sequence<String>): Int {
        return parseInput(lines).invoke2()
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