import Advent9.advent9a
import Advent9.advent9b
import java.io.File

fun main() {
    File("input/input9.txt").useLines {
        println(advent9a(it))
        println(advent9b(it))
    }
}

object Advent9 {
    class Matrix(val rows: List<String>) {
        fun value(c:Int, r:Int):Int {
            if (r < 0 || r >= rows.size) return Int.MAX_VALUE
            if (c < 0 || c >= rows[r].length) return Int.MAX_VALUE
            return rows[r][c].code - '0'.code
        }
        fun low() : List<Int> {
            return rows.mapIndexed { iRow, r->
                r.mapIndexedNotNull { iCol, c ->
                    val v = value(iCol,iRow)
                     if (v < value(iCol-1,iRow) &&
                             v < value(iCol+1,iRow) && v < value(iCol,iRow-1)
                         && v < value(iCol,iRow+1)) v+1 else null

                }
            }.flatten()
        }
    }
    fun advent9a(lines: Sequence<String>): Int {
        val matrix = Matrix(lines.toList())
        return matrix.low().sum()
    }

    fun advent9b(lines: Sequence<String>): Int {
        return 0
    }
}