import Day14.part1
import Day14.part2
import java.io.File

fun main() {
    File("input/input14.txt").useLines {
        println(part1(it))
        println(part2(it))
    }
}

object Day14 {
    class Template(line:String, val instructions: List<Instruction>) {
        private var elements:List<Char> = line.filter { it in 'A'..'Z' }.toList()

        fun invoke() {

            elements = elements.drop(1).fold(elements.take(1)) { els, e -> els + invoke(els.last(),e)}
        }
        fun invoke(from:Char,to:Char): List<Char> =
            instructions.firstOrNull { it.from == from && it.to == to}?.let { listOf(it.insert, it.to)} ?: listOf(to)

        fun result():Int  {
            val g = elements.groupingBy { it }.eachCount()
            return g.maxOf { it.value } - g.minOf { it.value }
        }

        override fun toString():String {
            return String(elements.toCharArray())
        }
    }
    data class Instruction(val from:Char, val to:Char, val insert:Char) {

    }
    fun part1(lines: Sequence<String>, steps:Int=10): Template {
        val template = parseInput(lines.toList())
        for (i in 1 .. steps) {
            template.invoke()
            println(template.toString())
        }

        return template
    }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }

    private fun parseInput(lines:List<String>):Template =
         Template(lines[0],parseInstruction(lines.drop(2)))
    private fun parseInstruction(list: List<String>): List<Instruction> {
        val reg = """([A-Z])([A-z]) -> ([A-Z])""".toRegex()
        return list.mapNotNull { l ->
            val match = reg.matchEntire(l)
            if (match != null)
                Instruction(match.groupValues[1][0],match.groupValues[2][0],match.groupValues[3][0])
            else null
        }

    }
}