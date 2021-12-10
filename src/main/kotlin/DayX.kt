import AdventX.part1
import AdventX.part2
import java.io.File

fun main() {
    File("input/inputX.txt").useLines {
        println(part1(it))
        println(part2(it))
    }
}

object AdventX {
    fun part1(lines: Sequence<String>): Int {
        return 0
    }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }
}