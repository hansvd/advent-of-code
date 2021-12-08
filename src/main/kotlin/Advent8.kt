import Advent8.advent8a
import Advent8.advent8b
import java.io.File

fun main() {
    File("input/input8.txt").useLines {
        println(advent8a(it))
        println(advent8b(it))
    }
}

object Advent8 {
    class Data(val uniqueSignals:List<String>,val output:List<String>) {
        init {
            require(uniqueSignals.size == 10 && output.size == 4)
        }
    }
    fun advent8a(lines: Sequence<String>): Int {
        //val segmentNb = arrayOf(6,2,5,5,4,5,6,3,7,6)
        val uniqueSegmentLength = arrayOf(2,4,3,7)

        return lines.fold(0) { n, line ->
            n + (parseLine(line)?.output?.count { uniqueSegmentLength.contains(it.length) } ?: 0)

        }

    }

    fun advent8b(lines: Sequence<String>): Int {
        return 0
    }


    private fun parseLine(s:String):Data? {
        val split = s.split('|')
        if (split.size != 2) return null
        return Data(split[0].trim().split(' '), split[1].trim().split(' '))
    }
}