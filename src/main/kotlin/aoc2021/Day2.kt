package aoc2021

import shared.Input

data class Move(val horizontal: Int, val vertical: Int)

fun parseInputLine(s: String): Move? {
    val reg = """\s*([^\s\d]+)\s(\d+)""".toRegex()
    val match = reg.matchEntire(s) ?: return null
    if (match.groups.size != 3) return null

    val d = match.groupValues[2].toInt()
    return when (match.groupValues[1]) {
        "forward" -> Move(d, 0)
        "backward" -> Move(-d, 0)
        "up" -> Move(0, -d)
        "down" -> Move(0, d)
        else -> null
    }
}

fun main() {

    Input.useLines(2021,2) {
        println(advent2b(it))
    }
}

/*
down X increases your aim by X units.
up X decreases your aim by X units.
forward X does two things:
It increases your horizontal position by X units.
It increases your depth by your aim multiplied by X.
 */
fun advent2b(lines: Sequence<String>): Int = lines
    .mapNotNull { s -> parseInputLine(s) }
    .fold(Triple(0, 0, 0)) { (x, y, aim), cur ->
        Triple(x + cur.horizontal, y + aim * cur.horizontal, aim + cur.vertical)
    }.let { (x, y, _) -> x * y }


fun advent2(lines: Sequence<String>): Int =
    lines.mapNotNull { parseInputLine(it) }
        .fold(Pair(0, 0)) { (x, y), cur -> Pair(x + cur.horizontal, y + cur.vertical) }
        .let { (x, y) -> x * y }