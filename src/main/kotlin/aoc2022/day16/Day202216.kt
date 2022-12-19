package aoc2022.day16

import shared.dijkstraSearch

object Day202216 {

    data class Valve(val name: String, val rate: Int, val tunnelsTo: List<String>)

    data class SearchStep(val valve: Valve, val timeLeft: Int, val previous: SearchStep?) {
        val name = valve.name
        override fun equals(other: Any?): Boolean = (other as? SearchStep)?.name == name
        override fun hashCode(): Int = name.hashCode()
        val stepNb: Int = (previous?.stepNb ?: -1) + 1
    }

    data class Node(val v: Valve, val stepNb: Int, val openValves: Set<Valve>, val score: Int, val timeLeft: Int, val previous:Node?) {
        override fun toString(): String = v.name + " - " + previous?.toString()
    }


    class Cave(private val valves: Map<String, Valve>) {

        private val searchMap = initSearch()
        fun invoke(): Int {
            val start = valves["AA"]!!
            val results = findRecur(Node(start,0, setOf(start),0,30,null))
            val r = results.maxBy { it.score }
            return r.score
        }

        fun invoke2(): Int {
            val start = valves["AA"]!!
            val startNode = Node(start,0, setOf(start),0,26,null)
//            // take 20: the right one is in one of the firsts
            return sequence {
                findRecur(startNode).sortedByDescending { it.score }.take(20).forEach {
                    var pNode:Node? = it
                    while(pNode != null && pNode.v != start) {
                        yield(pNode)
                        pNode = pNode.previous
                    }
                }
            }.map { r1 ->
                val result2 = findRecur(Node(start,0, r1.openValves,0,26,null))
                result2.map { Pair(r1, it) }
            }.maxOfOrNull { q ->
                q.maxOfOrNull { p ->
                    p.first.score + p.second.score
                } ?: 0
            } ?: 0

        }

        private fun findRecur(node:Node): List<Node> {
            if (node.timeLeft < 2)
                return listOf() // no time to open another valve
            val ends = valves.values.filter { it.rate > 0 && !node.openValves.contains(it) }.sortedByDescending { it.rate }
            val results =
                ends.mapNotNull { end ->
                    val steps = searchMap[node.v.name to end.name]
                    if (steps == null) null
                    else {
                        val tl = node.timeLeft - steps - 1
                        if (tl < 0) null
                        else Node(end, node.stepNb + steps, node.openValves + end, node.score + end.rate * tl, tl, node)
                    }
                }.sortedByDescending { it.score }
            val endResults = mutableListOf<Node>()
            for (r in results) {
                val result2 = findRecur(r)
                if (result2.isEmpty())
                    endResults.add(r)
                endResults.addAll(result2)
            }
            if (endResults.isEmpty())
                endResults.add(node)
            return endResults
        }


        private fun initSearch(): Map<Pair<String, String>, Int> {
            val start = valves["AA"]!!
            val ends = valves.values.filter { it.rate > 0 } + start
            val valMap = mutableMapOf<Pair<String, String>, Int>()
            ends.forEach { e1 ->
                ends.filter { it != e1 && it != start  }.forEach { e2 ->
                    val r = findFromTo(e1, e2)
                    if (r != null) valMap[Pair(e1.name, e2.name)] = r
                }
            }
            return valMap
        }

        private fun findFromTo(start: Valve, end: Valve): Int? {
            val r = dijkstraSearch(
                init = SearchStep(start, 30, null),
                found = { step -> step.timeLeft <= 1 || step.valve.name == end.name },
                children = { step -> step.valve.tunnelsTo.map { SearchStep(valves[it]!!, step.timeLeft - 1, step) } },
                cost = { it.stepNb }
            ).firstOrNull() ?: return null

            if (r.name != end.name) return null
            return r.stepNb
        }
    }


    fun part1(lines: Sequence<String>): Int = parseInput(lines).invoke()

    fun part2(lines: Sequence<String>): Int = parseInput(lines).invoke2()

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