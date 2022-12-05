package shared

import java.io.File

object Input {
    fun <T> useLines(year: Int, name: String, block: (Sequence<String>) -> T): T =
        File("src/main/kotlin/aoc$year/$name.txt").useLines { block(it) }

    fun <T> useLines(year: Int, nb: Int, block: (Sequence<String>) -> T): T =
        File("src/main/kotlin/aoc$year/day${(if (nb < 10) "0" else "") + nb}/$nb.txt").useLines { block(it) }

    fun <T> getLines(year: Int, nb: Int, block: (List<String>) -> T): T =
        File("src/main/kotlin/aoc$year/day${(if (nb < 10) "0" else "") + nb}/$nb.txt").useLines { block(it.toList()) }
    fun <T> getLines(year: Int, nb: Int, name:String, block: (List<String>) -> T): T =
        File("src/main/kotlin/aoc$year/day${(if (nb < 10) "0" else "") + nb}/$name.txt").useLines { block(it.toList()) }
}