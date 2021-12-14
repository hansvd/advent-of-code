import Day14.day14
import java.io.File

fun main() {
    File("input/input14.txt").useLines {
        println(day14(it))
        println(day14(it))
    }
}

data class Combo(val from:Char, val to:Char)
typealias ComboMap = HashMap<Combo,Long>
fun ComboMap.add(combo:Combo, count:Long = 1) {
    val c = this[combo]
    if (c == null) this[combo] = count else this[combo] = c + count
}
fun ComboMap.sub(combo:Combo, count:Long = 1) { this.add(combo,-count)}

object Day14 {

    class Template(line:String, private val instructions: List<Instruction>) {
        private var combinations:ComboMap = hashMapOf()

        init {
            for (i in 0 until line.length-1) {
                combinations.add(Combo(line[i],line[i+1]))
            }
            combinations.add(Combo(line.last(),'$')) // end of line, so last char is also counted
        }

        fun invoke() {
            val newCombos =  HashMap(combinations)
            for (c in combinations.keys) instructions.firstOrNull { it.from == c.from && it.to == c.to}?.let {
                val count = combinations[c] ?: 0
                newCombos.add(Combo(it.from,it.insert), count)
                newCombos.add(Combo(it.insert,it.to), count)
                newCombos.sub(Combo(it.from, it.to), count)
            }
            combinations = newCombos
        }


        fun result():Long  {
            val g = ((combinations.toList().groupBy ({ it.first.from } , {it.second})) ).
                    map { it.key to it.value.sumOf { count -> count } }
            return g.maxOf { it.second } - g.minOf { it.second }
        }
    }
    data class Instruction(val from:Char, val to:Char, val insert:Char)

    fun day14(lines: Sequence<String>, steps:Int=10): Template {
        val template = parseInput(lines.toList())
        repeat(steps) { template.invoke() }
        return template
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