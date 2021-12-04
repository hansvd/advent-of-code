import advent5.advent5a
import advent5.advent5b
import java.io.File

fun main() {
    File("input/input5.txt").useLines {
        println(advent5a(it))
        println(advent5b(it))
    }
}

object advent5 {
    fun advent5a(lines: Sequence<String>): Int {
        return 0
    }

    fun advent5b(lines: Sequence<String>): Int {
        return 0
    }
}