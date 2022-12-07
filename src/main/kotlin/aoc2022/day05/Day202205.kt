package aoc2022.day05


import shared.Stack
import shared.peek
import shared.pop
import shared.push

class Day202205(private val stacks:List<Stack<Char>>,
                private val moves: List<Move>) {

    data class Move(val nb:Int, val from:Int, val to:Int)

    fun crateMover9000():Day202205 {
        moves.forEach { m ->
            repeat(m.nb) {
                val v = stacks[m.from-1].pop()
                if (v != null) stacks[m.to-1].push(v)
            }
        }
        return this
    }
    fun crateMover9001():Day202205 {
        moves.forEach { m ->
            (0 until m.nb).mapNotNull { stacks[m.from-1].pop() }.reversed().forEach {
                stacks[m.to-1].push(it)
            }
        }
        return this
    }
    fun getResult():String = stacks.map{it.peek() ?: ' '}.joinToString(separator = "") { it.toString()}


    companion object {
        fun part1(lines: List<String>): String = parseInput(lines).crateMover9000().getResult()

        fun part2(lines: List<String>): String = parseInput(lines).crateMover9001().getResult()

        fun parseInput(lines: List<String>):Day202205 {
            val iSplit = lines.indexOfFirst { it.isBlank() }

            return Day202205(
                parseStacks(lines.subList(0,iSplit)),
                parseMoves(lines.subList(iSplit+1,lines.size)))
        }
        private fun parseStacks(lines:List<String>):List<Stack<Char>> {
            val stacks = lines.maxOfOrNull { (it.length + 1) / 4 } ?: 0
            val result = List(stacks) { mutableListOf<Char>() }
            lines.reversed().forEach { l ->
                l.chunked(4).forEachIndexed { index, crate ->
                    if (crate.startsWith('[')) {
                        result[index].add(crate[1])
                    }
                }
            }
            return result
        }
        private fun parseMoves(lines:List<String>):List<Move> = lines.mapNotNull {
            if (it.startsWith("move")) {
                val s = it.split(' ')
                Move(s[1].toInt(), s[3].toInt(), s[5].toInt())
            } else null
        }
    }
}