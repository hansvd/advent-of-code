import Day11.part1
import Day11.part2
import java.io.File

fun main() {
    File("input/input11.txt").useLines {
        println(part1(it))
        println(part2(it))
    }
}

object Day11 {
    class Grid(val rows: List<MutableList<Int>>) {
        var flashed = 0
        fun step() {
            rows.forEach { r -> for (i in 0 until r.size) r[i] = r[i] + 1 }
            val flashing = mutableListOf<Pair<Int, Int>>()
            do {
                val f = rows.mapIndexed { iRow, r ->
                    r.mapIndexedNotNull { iCol, value ->
                        if (value == 10) Pair(
                            iRow,
                            iCol
                        ) else null
                    }
                }.flatten().minus(flashing)

                f.forEach { addEnergyFrom(it.first, it.second) }
                flashing.addAll(f)
            } while (f.isNotEmpty())

            flashed += rows.sumOf { r -> r.sumOf { if (it > 9) 1.toInt() else 0 } }
            rows.forEach { r -> for (i in 0 until r.size) if (r[i] > 9) r[i] = 0 }

        }

        private fun addEnergyFrom(iRow: Int, iCol: Int) {

            for (r in iRow - 1..iRow + 1) for (c in iCol - 1..iCol + 1) {
                if (c < 0 || r < 0 || r >= rows.size) continue
                if (c >= rows[r].size) continue
                if (rows[r][c] == 10) continue
                rows[r][c] += 1
            }
        }
    }

    fun part1(lines: Sequence<String>, steps: Int = 100): Int {
        val grid = readInput(lines)
        for (i in 1..steps) grid.step()
        return grid.flashed
    }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }

    private fun readInput(lines: Sequence<String>): Grid = Grid(lines.map { line ->
        line.map { it.code - '0'.code }.toMutableList()
    }.toList())

}