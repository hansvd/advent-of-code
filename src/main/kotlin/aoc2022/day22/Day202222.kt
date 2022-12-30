package aoc2022.day22

import shared.Point

object Day202222 {

    const val Right = 0
    const val Down = 1
    const val Left = 2
    const val Up = 3

    const val Clockwise: Char = 'R'
   // val counterclockwise = 'L'

    class Instruction(val count:Int, val turn:Char)
    class Board(private val rows:List<List<Char>>, private val instructions:List<Instruction>) {
        //val height = rows.size
        val width = rows[0].size
        private var curPos = Point(rows[0].indexOfFirst { it != ' ' },0)
        private var facing = Right

        private fun xRangeFor(y: Int): IntRange =
            rows[y].let { row -> row.indexOfFirst { it != ' ' } ..row.indexOfLast { it != ' ' } }
        private fun yRangeFor(x: Int): IntRange =
            rows.indexOfFirst { it[x] != ' ' } .. rows.indexOfLast { it[x] != ' ' }

        private fun get(p:Point) = rows[p.y][p.x]

        fun run():Int {

            instructions.forEach { instruction ->
                when (facing) {
                    Right -> {
                        repeat(instruction.count) {
                            val newPos = curPos.right(xRangeFor(curPos.y))
                            if (get(newPos) == '.') curPos = newPos else return@repeat
                        }
                        facing = if (instruction.turn == Clockwise) Down else Up
                    }
                    Left -> {
                        repeat(instruction.count) {
                            val newPos = curPos.left(xRangeFor(curPos.y))
                            if (get(newPos) == '.') curPos = newPos else return@repeat
                        }
                        facing = if (instruction.turn == Clockwise) Up else Down
                    }
                    Down -> {
                        repeat(instruction.count) {
                            val newPos = curPos.down(yRangeFor(curPos.x))
                            if (get(newPos) == '.') curPos = newPos else return@repeat
                        }
                        facing = if (instruction.turn == Clockwise) Left else Right
                    }
                    Up -> {
                        repeat(instruction.count) {
                            val newPos = curPos.up(yRangeFor(curPos.x))
                            if (get(newPos) == '.') curPos = newPos else return@repeat
                        }
                        facing = if (instruction.turn == Clockwise) Right else Left
                    }
                }

            }
            return 1000 * (curPos.y + 1) + 4 * (curPos.x + 1) + facing
        }



    }
    fun part1(lines: List<String>): Int = parseInput(lines).run()

    fun part2(lines: List<String>): Int {
        parseInput(lines)
        return 0
    }

    fun parseInput(lines:List<String>):Board {
        val board = lines.take(lines.size - 2)
        val width = board.maxOf { it.length }
        val rows = board.map { row ->
            row.padEnd(width).map { it }
        }

        val instructions = """(\d+)([LR])""".toRegex().findAll(lines.last()).map {
            Instruction(it.groupValues[1].toInt(),it.groupValues[2][0])
        }
        return Board(rows,instructions.toList())
    }
}