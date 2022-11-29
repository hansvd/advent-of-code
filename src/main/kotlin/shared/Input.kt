package shared

import java.io.File

object Input {
    fun <T> useLines(year: Int, name: String, block: (Sequence<String>) -> T): T =
        File("input/aoc$year/$name.txt").useLines { block(it) }

    fun <T> useLines(year: Int, nb: Int, block: (Sequence<String>) -> T): T =
        File("input/aoc$year/$nb.txt").useLines { block(it) }

    fun <T> getLines(year: Int, nb: Int, block: (List<String>) -> T): T =
        File("input/aoc$year/$nb.txt").useLines { block(it.toList()) }
}