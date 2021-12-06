import Advent6.advent6a
import Advent6.advent6b
import java.io.File

fun main() {
    File("input/input6.txt").useLines {
        println(advent6a(it.first()))
        println(advent6b(it))
    }
}

object Advent6 {
    fun advent6a(line: String, days:Int=80): Int {
        var cur = line.split(',').mapNotNull { tryParseInt(it) }.map {it}

        for (day in 1..days) {
            cur = nextDay(cur)
        }
        return cur.size
    }

    fun advent6b(lines: Sequence<String>): Int {
        return 0
    }

    private fun nextDay(list: List<Int>):List<Int> {

        return list.flatMap { i -> if (i > 0) listOf(i-1) else listOf(6,8) }
    }
}