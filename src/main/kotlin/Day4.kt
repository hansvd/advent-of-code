import Advent4.advent4a
import Advent4.advent4b
import java.io.File

fun main() {
    val lines = File("input/input4.txt").readLines()

        println(advent4a(lines))
        println(advent4b(lines))
}

object Advent4 {
    class Cell(val n: Int, var done: Boolean = false)
    class Board(private val rows: List<List<Cell>>) {
        private val colNb: Int = rows.minOf { it.size }

        var done:Boolean = false

        fun play(n: Int) {
            rows.forEach { r ->
                r.forEach { if (it.n == n) it.done = true }
            }
        }

        fun wins(): Boolean =
            (rows.any { r -> r.all { it.done } }) || (0 until colNb).any { c -> rows.all { r -> r[c].done } }

        fun score(): Int = rows.sumOf { r -> r.mapNotNull { if (it.done) null else it.n }.sum() }
    }

    fun advent4a(lines: List<String>): Int {

        val (numbers, boards) = readInput(lines)
        numbers.forEach { n ->
            boards.forEach {
                it.play(n)
                if (it.wins()) return it.score()*n
            }
        }
        return 0
    }
    fun advent4b(lines: List<String>): Int {
        val (numbers, boards) = readInput(lines)
        var lastWon: Pair<Board?,Int> = Pair(null,0)

        numbers.forEach { n ->
            lastWon = playNumber(boards, n, lastWon)
            if (boards.all { it.done}) return@forEach
        }
        return (lastWon.first?.score() ?: 0) * lastWon.second
    }

    private fun playNumber(boards: MutableList<Board>, n: Int, lastWon: Pair<Board?,Int>):Pair<Board?,Int> {
        var lastWon1 = lastWon

        boards.forEach {
            if (it.done) return@forEach
            it.play(n)
            if (it.wins()) {
                it.done = true
                lastWon1 = Pair(it,n)
            }
        }
        return lastWon1
    }

    private fun readInput(lines: List<String>): Pair<List<Int>, MutableList<Board>> {
        val numbers = lines.first().split(',').mapNotNull { tryParseInt(it) }
        val boards = mutableListOf<Board>()
        var curBoard = mutableListOf<List<Cell>>()
        lines.drop(1).forEach { line ->
            if (line.isBlank()) {
                if (curBoard.size > 0) {
                    boards.add(Board(curBoard))
                    curBoard = mutableListOf()
                }
                return@forEach
            }
            val row = line.split(' ').mapNotNull { tryParseInt(it) }.map { Cell(it) }
            if (row.isNotEmpty()) curBoard.add(row)
        }
        if (boards.size > 0) {
            boards.add(Board(curBoard))
        }
        return Pair(numbers, boards)
    }


}