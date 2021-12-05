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
    data class Line(val x0:Int, val y0:Int, val x1:Int, val y1:Int) {
        val xMin = if (x0 < x1) x0 else x1
        val xMax = if (x0 > x1) x0 else x1
        val yMin = if (y0 < y1) y0 else y1
        val yMax = if (y0 > y1) y0 else y1

        val isHorizontal = y0 == y1
        val isVertical = x0 == x1
        val isDiagonal45 = xMax - xMin == yMax - yMin
    }
    class Matrix(lines:List<Line>, val useDiagonal:Boolean) {
        val w = lines.maxOf { maxOf(it.x0,it.x1) } + 1
        val h = lines.maxOf {  maxOf(it.y0, it.y1) } + 1
        val content = Array(h) { Array(w) { 0 } }
        init {
            lines.forEach { addLine(it) }
        }
        
        fun addLine(l:Line) {
            require(l.x1 < w && l.y1 < h)
            if (l.isHorizontal) for (i in l.xMin .. l.xMax) { content[l.y0][i] ++ }
            else if (l.isVertical) for (i in l.yMin .. l.yMax) { content[i][l.x0] ++ }
            else if (useDiagonal && l.isDiagonal45) {
                if (l.x0 <= l.x1 && l.y0 <= l.y1 || (l.x0 >= l.x1 && l.y0 >= l.y1)) {
                    var x = l.xMin
                    for (i in l.yMin..l.yMax) {
                        content[i][x++]++
                    }
                } else {
                    var x = l.xMax
                    for (i in l.yMin..l.yMax) {
                        content[i][x--]++
                    }
                }
            }
        }

        fun countAtLeast(i:Int):Int {
            return content.sumOf { r->r.map { if (it >= i) 1 else 0 }.sum() }
        }
    }
    fun advent5a(input: Sequence<String>): Int = Matrix(input.mapNotNull { parseLine(it) }.toList(), false).countAtLeast(2)

    fun advent5b(input: Sequence<String>): Int = Matrix(input.mapNotNull { parseLine(it) }.toList(), true).countAtLeast(2)



    fun parseLine(s:String):Line? {
        val reg = """\s*(\d+),(\d+)\s*->\s*(\d+),(\d+)""".toRegex()
        val match = reg.matchEntire(s) ?: return null
        return Line(match.groupValues[1].toInt(), match.groupValues[2].toInt(),
                    match.groupValues[3].toInt(), match.groupValues[4].toInt())
    }
}