package aoc2021

val Char.isEast get() = this == '>'
val Char.isSouth get() = this == 'v'

object Day25 {


    data class Point(val x: Int, val y: Int) {

        fun goEast(width: Int) = copy(x = if (x + 1 >= width) 0 else x + 1)
        fun goSouth(height: Int) = copy(y = if (y + 1 >= height) 0 else y + 1)

        override fun toString(): String = "($x,$y)"
    }

    data class SeeBottom(val cucumbers: Map<Point, Char>, val width: Int, val height: Int) {


        fun step() = goEast().goSouth()

        private fun goEast(): SeeBottom = copy(cucumbers = cucumbers.map {
            (if (it.value.isEast && cucumbers[it.key.goEast(width)] == null)
                it.key.goEast(width) else it.key) to it.value
        }.toMap())

        private fun goSouth(): SeeBottom = copy(
            cucumbers =
            cucumbers.map {
                (if (it.value.isSouth && cucumbers[it.key.goSouth(height)] == null)
                    it.key.goSouth(height) else it.key) to it.value
            }.toMap()
        )

        fun maxSteps(i:Int = 0): Int {
            val next = step()
            if (next == this) return i+1
            return next.maxSteps(i+1)
        }

        override fun toString(): String = sequence {
            (0 until height).forEach { y ->
                if (y > 0) yield('\n')
                (0 until width).forEach { x ->
                    val c = cucumbers[Point(x, y)]
                    if (c == null) yield('.') else yield(c)
                }
            }
        }.joinToString(separator = "")
    }
    fun parseInput(lines: List<String>): SeeBottom {

        val map = mutableMapOf<Point, Char>()
        lines.forEachIndexed { y, row ->
            row.forEachIndexed { x, c -> if (c == 'v' || c == '>') map[Point(x, y)] = c }
        }
        return SeeBottom(map, lines[0].length, lines.size)
    }
}