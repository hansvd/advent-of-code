package shared

import java.io.File

object Input {
    fun <T> useLines(year: Int, name: String, block: (Sequence<String>) -> T): T =
        File("src/main/kotlin/aoc$year/$name.txt").useLines { block(it) }

    fun <T> useLines(year: Int, day: Int, block: (Sequence<String>) -> T): T =
        File("src/main/kotlin/aoc$year/day${(if (day < 10) "0" else "") + day}/$day.txt").useLines { block(it) }

    fun <T> useLines(year: Int, day: Int, name:String, block: (Sequence<String>) -> T): T =
        File("src/main/kotlin/aoc$year/day${(if (day < 10) "0" else "") + day}/$name.txt").useLines { block(it) }

    fun <T> useText(year: Int, day: Int, block: (String) -> T): T =
        block(File("src/main/kotlin/aoc$year/day${(if (day < 10) "0" else "") + day}/$day.txt").readText())

    fun <T> useText(year: Int, day: Int, name:String, block: (String) -> T): T =
        block(File("src/main/kotlin/aoc$year/day${(if (day < 10) "0" else "") + day}/$name.txt").readText())

    fun <T> getLines(year: Int, day: Int, block: (List<String>) -> T): T =
        File("src/main/kotlin/aoc$year/day${(if (day < 10) "0" else "") + day}/$day.txt").useLines { block(it.toList()) }
    fun <T> getLines(year: Int, day: Int, name:String, block: (List<String>) -> T): T =
        File("src/main/kotlin/aoc$year/day${(if (day < 10) "0" else "") + day}/$name.txt").useLines { block(it.toList()) }
}