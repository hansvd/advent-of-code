package aoc2021

import aoc2021.Advent9.advent9a
import aoc2021.Advent9.advent9b
import java.io.File

fun main() {
    File("input/aoc2021/input9.txt").useLines {
        println(advent9a(it))
    }
    File("input/aoc2021/input9.txt").useLines {
        println(advent9b(it))
    }
}

object Advent9 {
    class Matrix(private val rows: List<String>) {
        private fun value(c: Int, r: Int): Int {
            if (r < 0 || r >= rows.size) return Int.MAX_VALUE
            if (c < 0 || c >= rows[r].length) return Int.MAX_VALUE
            return rows[r][c].code - '0'.code
        }
        private fun isLow(iCol:Int, iRow:Int):Boolean {
            val v = value(iCol, iRow)
            return (v < value(iCol - 1, iRow) &&
                v < value(iCol + 1, iRow) && v < value(iCol, iRow - 1)
                && v < value(iCol, iRow + 1)
            )
        }
        fun low(): List<Int> {
            return rows.mapIndexed { iRow, r ->
                r.mapIndexedNotNull { iCol, _ ->
                    if (isLow(iCol,iRow)) value(iCol, iRow) + 1 else null
                }
            }.flatten()
        }
        fun basinSizes():List<Int> {
            val r = rows.mapIndexed { iRow, r ->
                r.mapIndexedNotNull { iCol, _ ->
                    if (isLow(iCol,iRow)) basinSize(iCol,iRow, mutableSetOf()) else null
                }
            }.flatten()
            return r
        }
        private fun basinSize(iCol: Int, iRow: Int, done:MutableSet<Pair<Int,Int>>): Int {
            if (done.contains(Pair(iCol,iRow))) return 0
            val v = value(iCol,iRow)
            if(v >= 9 || (done.isNotEmpty() && isLow(iCol,iRow))) return 0

            done.add(Pair(iCol,iRow))
            return 1 + basinSize(iCol-1,iRow, done)  +
                    basinSize(iCol+1,iRow,done)  +
                    basinSize(iCol,iRow-1, done)  +
                    basinSize(iCol, iRow+1,done)

        }
    }

    fun advent9a(lines: Sequence<String>): Int {
        val matrix = Matrix(lines.toList())
        return matrix.low().sum()
    }

    fun advent9b(lines: Sequence<String>): Int {
        val matrix = Matrix(lines.toList())
        return matrix.basinSizes().sortedDescending().take(3).fold(1) { r, i -> r*i }
    }
}