import Day13.part1
import Day13.part2
import java.io.File

fun main() {
    val lines = File("input/input13.txt").readLines()
    println(part1(lines, 1))
    println(part2(lines))
}


object Day13 {
    data class Dot(val x: Int, val y: Int)
    data class Fold(val isVertical: Boolean, val value: Int)
    class Pattern(dots: List<Dot>, private val w:Int, private val h:Int) {
        constructor(dots: List<Dot>) : this (dots, dots.maxOf { it.x } + 1,dots.maxOf { it.y } + 1)
        constructor(dots: Sequence<Dot>, w:Int, h:Int) : this(dots.toList(),w,h)

        private val pattern = Array(w) { BooleanArray(h) }

        init {
            dots.forEach { pattern[it.x][it.y] = true }
        }

        val dotNb get() = pattern.sumOf { r -> r.count { it } }

        override fun toString(): String {
            return sequence { for (y in 0 until  h) {
                (0 until w).forEach { x -> yield(if (pattern[x][y]) '#' else '.') }
               yield('\n')
            }}.joinToString("")
        }
        fun invoke(folds: List<Fold>): Pattern {
            return fold(folds.first())
        }

        fun fold(f: Fold): Pattern {
            return if (f.isVertical) foldVertical(f.value) else foldHoriz(f.value)
        }

        private fun foldVertical(above: Int): Pattern {
            val below = h - above - 1
            val foldH = maxOf(above, below)
            return Pattern(sequence {
                (0 until above).forEach { y ->
                    (0 until w).filter { x -> pattern[x][y] }.forEach { x-> yield(Dot(x,y+(foldH-above))) }
                }
                (0 until below).forEach { y ->
                    (0 until w).filter { x -> pattern[x][h - (1+y)] }.forEach { x-> yield(Dot(x,y+(foldH-below))) }
                }

            }, w=w, h=foldH)
        }

        private fun foldHoriz(left: Int): Pattern {
            val right = w - 1 - left
            val foldWidth = maxOf(left, right)
            return Pattern(sequence {
                (0 until left).forEach { x ->
                    (0 until h).filter { y -> pattern[x][y] }.forEach { y-> yield(Dot(x+(foldWidth-left),y)) }
                }
                (0 until right).forEach { x ->
                    (0 until h).filter { y -> pattern[w - (1 + x)][y] }.forEach { y-> yield(Dot(x+(foldWidth-right),y)) }
                }
            }, foldWidth,h)

        }
    }

    fun part1(lines: List<String>, foldNb: Int = 1): Int {
        val (pattern, folds) = parseLines(lines)
        return folds.take(foldNb).fold(pattern) { p, f -> p.fold(f) }.dotNb
    }

    fun part2(lines: List<String>): String {
        val (pattern, folds) = parseLines(lines)
        return folds.fold(pattern) { p, f -> p.fold(f) }.toString()
    }

    private fun parseLines(lines: List<String>): Pair<Pattern, List<Fold>> {
        val sep = lines.indexOfFirst { it.isBlank() }
        val dots = parseDots(lines.subList(0, sep))
        val folds = parseFolds(lines.subList(sep + 1, lines.size))
        return Pair(Pattern(dots), folds)
    }

    private fun parseDots(list: List<String>): List<Dot> {
        return list.mapNotNull { l ->
            val s = l.split(',')
            if (s.size == 2) { Dot(s[0].toInt(), s[1].toInt()) } else null
        }
    }

    private fun parseFolds(list: List<String>): List<Fold> {
        val reg = """fold along ([x,y])=(\d+)""".toRegex()
        return list.mapNotNull { l ->
            val match = reg.matchEntire(l)
            if (match != null)
                Fold(match.groupValues[1] == "y", match.groupValues[2].toInt())
            else null
        }

    }
}