import java.io.File

data class Move(val horizontal:Int, val vertical: Int)

fun parseInstruction(s:String):Move? {
    val reg = """\s*([^\s\d]+)\s(\d+)""".toRegex()
    val match = reg.matchEntire(s) ?: return null
    if (match.groups.size != 3) return null

    val d = match.groupValues[2].toInt()
    return when(match.groupValues[1]) {
        "forward" -> Move(d,0)
        "backward" -> Move(-d,0)
        "up" -> Move(0,-d)
        "down" -> Move(0,d)
        else -> null
    }
}
fun main() {

    File("input2.txt").useLines {
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
fun advent2b(lines:Sequence<String>):Int {
    var x = 0
    var y = 0
    var aim = 0
    lines.forEach { s ->
        val cur = parseInstruction(s)
        if (cur == null) {
            println("Unexpected input: $s")
            return@forEach
        }
        x += cur.horizontal
        y += aim*cur.horizontal
        aim += cur.vertical
    }
    return x*y
}


fun advent2(lines:Sequence<String>):Int {
    var x = 0
    var y = 0
    lines.forEach { s ->
        val cur = parseInstruction(s)
        if (cur == null) {
            println("Unexpected input: $s")
            return@forEach
        }
        x += cur.horizontal
        y += cur.vertical
    }
    return x*y
}