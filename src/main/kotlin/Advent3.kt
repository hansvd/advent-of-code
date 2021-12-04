import Advent3.advent3
import Advent3.advent3b
import java.io.File

fun main() {

    File("input/input3.txt").useLines {
        println(advent3(it))
        println(advent3b(it))
    }
}

object Advent3 {
    
    private fun parseInputLine(s: String): List<Int>? {
        val reg = """\s*([0,1]+)""".toRegex()
        val match = reg.matchEntire(s) ?: return null
        if (match.groupValues.size != 2) return null

        val result = match.groupValues[1].mapNotNull {
            if (it == '0') 0 else if (it == '1') 1 else null
        }
        if (result.size < 5) return null
        return result
    }

    fun advent3(lines: Sequence<String>): Int {
        val list = lines.mapNotNull { parseInputLine(it) }.toList()
        val bitNb = list.minOf { it.size }
        val threshold = list.size / 2
        val tot = (0 until bitNb).map { i-> list.sumOf { it[i] } }
        val gamma = tot.map { t -> if (t > threshold) 1 else 0 }.fold(0) { x, cur -> (x shl 1) + cur }
        val epsilon = tot.map { t -> if (t <= threshold) 1 else 0 }.fold(0) { x, cur -> (x shl 1) + cur}
        return gamma*epsilon
    }


    /*
    Keep only numbers selected by the bit criteria for the type of rating value for which you are searching. Discard numbers which do not match the bit criteria.
    If you only have one number left, stop; this is the rating value for which you are searching.
    Otherwise, repeat the process, considering the next bit to the right.
    The bit criteria depends on which type of rating value you want to find:

    To find oxygen generator rating, determine the most common value (0 or 1) in the current bit position, and keep only numbers with that bit in that position. If 0 and 1 are equally common, keep values with a 1 in the position being considered.
    To find CO2 scrubber rating, determine the least common value (0 or 1) in the current bit position, and keep only numbers with that bit in that position. If 0 and 1 are equally common, keep values with a 0 in the position being considered.
    */
    fun advent3b(lines:Sequence<String>):Int {
        val list = lines.mapNotNull { parseInputLine(it) }.toList()
        val oxygen = getRating(list,false)
        val scrub = getRating(list,true)
        return oxygen*scrub
    }


    private fun getRating(list:List<List<Int>>, leastCommon: Boolean): Int{
        val bitNb = list.minOf { it.size }
        var newList = list
        for (i in 0 until bitNb) {
            val tot = newList.sumOf { it[i] }
            val threshold = (newList.size*2) / 2

            val f = if (leastCommon) {if ((tot*2)  < threshold) 1 else 0 } else { if ((tot*2) >= threshold) 1 else 0 }
            newList =  newList.filter { it[i] == f }

            if (newList.size <= 1) break
        }
        require(newList.size == 1)
        return newList[0].fold(0) { x, cur -> (x shl 1) + cur }
    }

}