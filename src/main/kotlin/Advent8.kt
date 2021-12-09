import Advent8.Data.Companion.uniqueSegmentLength
import Advent8.advent8a
import Advent8.advent8b
import java.io.File

fun main() {
    File("input/input8.txt").useLines {
        println(advent8a(it))
    }
    File("input/input8.txt").useLines {
        println(advent8b(it))
    }
}

object Advent8 {
    fun advent8a(lines: Sequence<String>): Int = lines.fold(0) { n, line ->
        n + (parseLine(line)?.output?.count { uniqueSegmentLength.contains(it.length) } ?: 0)
    }

    fun advent8b(lines: Sequence<String>): Int = lines.fold(0) { n, line ->
        n + (parseLine(line)?.getResult() ?: 0)
    }


    class Data(private val uniqueSignals: List<String>, val output: List<String>) {

        companion object {

            val uniqueSegmentLength = arrayOf(2, 4, 3, 7)
            val numbers = arrayOf(
                "abcefg", //0
                "cf", // 1
                "acdeg", // 2
                "acdfg", // 3
                "bcdf", // 4
                "abdfg", // 5
                "abdefg", //6
                "acf", //7
                "abcdefg", // 8
                "abcdfg" // g
            )

        }

        // possible unique Signal to number mappings,
        // start by comparing length
        private val signalToNumberMappings =
            Array(10) { uIndex -> numbers.mapIndexedNotNull { index, s -> if (s.length == uniqueSignals[uIndex].length) index else null } }

        // map from signal char to number char
        private val charMapping: MutableMap<Char, Set<Char>> =
            ('a'..'g').associateWith { ('a'..'g').toSet() }.toMutableMap()

        init {
            require(uniqueSignals.size == 10 && output.size == 4)
            @Suppress("ControlFlowWithEmptyBody")
            reducePossibleMappings()
            require(signalToNumberMappings.all { it.size == 1})
        }

        fun getResult():Int = output.fold(0) { r, o -> r * 10 + mapOutputToNumber(o)}

        private fun mapOutputToNumber(output:String): Int {
            val i = uniqueSignals.indexOfFirst { it.toCharArray().sorted() == output.toCharArray().sorted() }
            require (i >= 0)
            return signalToNumberMappings[i].first()
        }
        private fun reducePossibleMappings() {
            var mappingCount = signalToNumberMappings.sumOf { it.size }
            do {
                if (mappingCount == 10) return

                reduceCharMappings()
                reduceNumberMappingsByCharMapping()
                removeDoubleNumberMappings()
                val newCount =  signalToNumberMappings.sumOf { it.size }
                if (newCount == mappingCount) return  // done
                mappingCount = newCount
            } while(true)
        }
        private fun reduceCharMappings()  {

            for (i in 0..9) {
                val possibleNumbers = signalToNumberMappings[i]
                val possibleChars = possibleNumbers.map { numbers[it] }.fold("") { s, n -> s + n }

                // reduce possible char mappings
                val sig = uniqueSignals[i]
                for (c in 'a'..'g') {
                    if (possibleChars.contains(c)) continue
                    for (e in sig)
                        charMapping[e] = charMapping[e]!!.filter { it != c }.toSet()
                }
            }
        }

        private fun reduceNumberMappingsByCharMapping() {

            uniqueSignals.forEachIndexed { indexUniqueNumbers, u ->
                val nums = signalToNumberMappings[indexUniqueNumbers].map { Pair(it, numbers[it]) }
                val mappings = getPossibleMappingsFor(u)
                val matches = nums.map { n ->
                    mappings.mapNotNull { m ->

                        if (n.second.toCharArray().toSet() == m) {
                            n.first
                        } else null
                    }
                }.flatten()


                signalToNumberMappings[indexUniqueNumbers].filter { !matches.contains(it) }.forEach { d ->
                    signalToNumberMappings[indexUniqueNumbers] =
                        signalToNumberMappings[indexUniqueNumbers].filter { it != d }
                }
            }
        }

        private fun removeDoubleNumberMappings() {
            for (i in 0..9) {
                if (signalToNumberMappings[i].size <= 1) continue
                signalToNumberMappings.filter {it.size == 1 && signalToNumberMappings[i].contains(it[0])}.forEach { d ->
                    signalToNumberMappings[i] = signalToNumberMappings[i].filter { it != d[0] }
                }
            }
        }

        private fun getPossibleMappingsFor(u: String): List<Set<Char>> {
            var r = mutableListOf<MutableSet<Char>>()
            u.forEachIndexed { i, c ->
                val m1 = charMapping[c]!!
                if (r.isEmpty())
                    m1.forEach { r.add(mutableSetOf(it)) }
                else {
                    val rnew = mutableListOf<MutableSet<Char>>()
                    r.forEach { rr -> m1.forEach { mm1 -> rnew.add(mutableSetOf(mm1).union(rr).toMutableSet()) } }
                    r = rnew
                }

                r = r.filter { it.size == i + 1 }.distinct().toMutableList()
            }

            return r.filter { it.size == u.length }
        }
    }


    private fun parseLine(s: String): Data? {
        val split = s.split('|')
        if (split.size != 2) return null
        return Data(split[0].trim().split(' '), split[1].trim().split(' '))
    }
}