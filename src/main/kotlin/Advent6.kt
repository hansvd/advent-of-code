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
        var cur = line.split(',').mapNotNull { tryParseInt(it) }.map { Pair(it,6)}

        for (day in 1..days) {
            cur = nextDay(cur)
//            cur.forEach { print(it.first.toString() + ",") }
//            println()
        }
        return cur.size
    }

    fun advent6b(lines: Sequence<String>): Int {
        return 0
    }

    private fun nextDay(list: List<Pair<Int, Int>>):List<Pair<Int,Int>> {
//        val l1 = mutableListOf<Pair<Int,Int>>()
//        val l2 = mutableListOf<Pair<Int,Int>>()
//        for (i in list) {
//            if (i.first > 0) {
//                l1.add(Pair(i.first-1,i.second))
//            } else {
//                l1.add(Pair(i.second,i.second))
//                l2.add(Pair(8,6))
//            }
//        }
//        return l1 + l2
        return list.flatMap { i -> if (i.first > 0) listOf(Pair(i.first-1,i.second)) else listOf(Pair(i.second, i.second), Pair(8,6)) }
    }
}