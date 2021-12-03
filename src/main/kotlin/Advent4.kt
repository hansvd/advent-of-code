import Advent4.advent4a
import Advent4.advent4b
import java.io.File

fun main() {
    File("input4.txt").useLines {
        println(advent4a(it))
        println(advent4b(it))
    }
}

object Advent4 {
    fun advent4a(lines: Sequence<String>): Int {
        return 0
    }

    fun advent4b(lines: Sequence<String>): Int {
        return 0
    }
}