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
    class Pattern(dots: List<Dot>) {
        constructor(dots: Sequence<Dot>) : this(dots.toList())

        private val w = dots.maxOf { it.x } + 1
        private val h = dots.maxOf { it.y } + 1
        private val pattern = Array(w) { BooleanArray(h) }

        init {
            dots.forEach { pattern[it.x][it.y] = true }
        }

        val dotNb get() = pattern.sumOf { r -> r.count { it } }
        fun invoke(folds: List<Fold>): Pattern {
            return fold(folds.first())
        }

        fun fold(f: Fold): Pattern {
            return if (f.isVertical) foldVertical(f.value) else foldHoriz(f.value)
        }

        private fun foldVertical(above: Int): Pattern {
            val below = h - above - 1
            return Pattern(sequence {
                (0 until maxOf(above, below)).forEach { y ->
                    val yy = above + below - y
                    if (yy in (above + 1) until h)
                        (0 until w).filter { x -> pattern[x][yy] }.forEach { x ->
                            yield(Dot(x, y))
                        }
                    if (y < above)
                        (0 until w).filter { x -> pattern[x][y] }.forEach { x ->
                            if (pattern[x][y]) yield(Dot(x, y))
                        }

                }

            })
        }

        private fun foldHoriz(left: Int): Pattern {
            val right = w - 1 - left

            return Pattern(sequence {
                for (x in 0 until maxOf(left, right)) {
                    val xx = left * 2 - x
                    if (xx in (left + 1) until w)
                        (0 until h)
                            .filter { pattern[xx][it] }
                            .forEach { yield(Dot(x, it)) }

                    if (x < left)
                        (0 until h)
                            .filter { pattern[x][it] }
                            .forEach { yield(Dot(x, it)) }

                }
            })

        }
    }


    fun part1(lines: List<String>, foldNb: Int = 1): Int {
        val (pattern, folds) = parseLines(lines)
        val p = folds.take(foldNb).fold(pattern) { p, f -> p.fold(f) }
        return p.dotNb
    }

    fun part2(lines: List<String>): Int {
        val (pattern, folds) = parseLines(lines)
        val p = folds.fold(pattern) { p, f -> p.fold(f) }
        return p.dotNb
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
            if (s.size != 2) null else {
                Dot(s[0].toInt(), s[1].toInt())
            }
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