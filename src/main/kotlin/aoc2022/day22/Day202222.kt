package aoc2022.day22

import shared.Direction
import shared.Direction.*
import shared.Point
import shared.Turn
import shared.Turn.Clockwise
import shared.Turn.Counterclockwise

object Day202222 {


    fun part1(lines: List<String>): Int = parseInput(lines).invoke()

    fun part2(lines: List<String>, isTest: Boolean): Int = parseInput(lines).toCube(isTest).invoke()

    data class Instruction(val count: Int, val turn: Turn)

    data class Rectangle(private val start: Point, val angle: Int, val rows: List<List<Char>>) {

        private val width = rows[0].size
        private val height = rows.size

        fun get(p: Point) = rows[p.y][p.x]

        fun turn90() = Rectangle(start, angle + 90, (0 until width).map { w ->
            (0 until height).map { h -> rows[w][h] }
        })

        fun turn180() = turn90().turn90()

        fun toBoardPoint(pos: Point): Point {
            println("ToBoardPoint $start, $pos")
            return when (angle) {
                0 -> Point(pos.x + start.x, pos.y + start.y)
                90 -> Point(pos.y + start.x, pos.x + start.y)
                180 -> Point(start.x + width - 1 - pos.x, start.y + height - 1 - pos.y)
                else -> throw NotImplementedError()
            }
        }

    }


    class Cube(private var sides: List<Rectangle>, private val instructions: List<Instruction>) {
        private var state = State(0, Point(sides[0].rows[0].indexOfFirst { it == '.' }, 0), Right)
        private val sideWidth = sides[0].rows.size
        private val sideMax = sideWidth - 1

        inner class State(private val side: Int, private val pos: Point, private val direction: Direction) {

            fun get() = sides[side].get(pos)

            override fun toString(): String = "side=$side, pos=$pos, direction=$direction"

            fun result(): Int {
                val curPos = sides[side].toBoardPoint(pos)
                println("Result for side=$side, pos=$pos -> $curPos, dir=${direction.id}")

                return 1000 * (curPos.y + 1) + 4 * (curPos.x + 1) + direction.id
            }

            fun turn(turn: Turn): State {
                return State(
                    side, pos,
                    direction.turn(turn)
                )

            }

            fun nextState(): State {
                val newPos = pos.next(direction)
                return if (newPos.x in 0..sideMax && newPos.y in 0..sideMax) State(side, newPos, direction)
                else when (side) {
                    0 -> {
                        when (direction) {
                            Right -> State(1, Point(sideMax, sideMax - pos.y), Left)
                            Left -> State(4, Point(pos.y,0), Down)
                            Down -> State(2, Point(pos.x, 0), Down)
                            Up -> State(3, Point(0, sideMax - pos.x), Down)
                        }
                    }

                    1 -> {
                        when (direction) {
                            Right -> State(0, Point(sideMax, sideMax - pos.y), Left)
                            Left -> State(5, Point(sideMax, pos.y), Left)
                            Down -> State(3, Point(sideMax, sideMax - pos.x), Left)
                            Up -> State(2, Point(sideMax, sideMax - pos.x), Left)
                        }
                    }

                    2 -> {
                        when (direction) {
                            Right -> State(1, Point(sideMax -pos.y,0), Down)
                            Left -> State(4, Point(sideMax, pos.y), Left)
                            Down -> State(5, Point(pos.x, 0), Down)
                            Up -> State(0, Point(pos.x, sideMax), Up)
                        }
                    }

                    3 -> {
                        when (direction) {
                            Right -> State(4, Point(0, pos.y), Right)
                            Left -> State(1, Point(sideMax-pos.y,sideMax), Up)
                            Down -> State(5, Point(sideMax - pos.x, sideMax), Up)
                            Up -> State(0, Point(sideMax-pos.x, 0), Down)
                        }
                    }

                    4 -> {
                        when (direction) {
                            Right -> State(2, Point(0, pos.y), Right)
                            Left -> State(3, Point(sideMax, pos.y), Left)
                            Down -> State(5, Point(0, sideMax - pos.x), Left)
                            Up -> State(0, Point(0, pos.x), Right)
                        }
                    }

                    5 -> {
                        when (direction) {
                            Right -> State(1, Point(0, pos.y), Right)
                            Left -> State(4, Point(sideMax, sideMax - pos.y), Up)
                            Down -> State(3, Point(sideMax - pos.x, sideMax), Up)
                            Up -> State(2, Point(pos.x, sideMax), Up)
                        }
                    }

                    else -> throw UnsupportedOperationException()
                }
            }

        }

        //val nextSides = mapOf(Pair(0, Right) to Pair(0, Right))
        fun invoke(): Int {

            instructions.forEach { instruction ->
                println(instruction)
                run repeatBlock@ {
                    repeat(instruction.count) {
                        val newState = state.nextState()
                        if (newState.get() == '.') {
                            state = newState
                            println(newState)
                        } else
                            return@repeatBlock
                    }
                }
                state = state.turn(instruction.turn)
                println("Turn $state")
            }
            return state.result()
        }
    }

    class Board(private val rows: List<List<Char>>, private val instructions: List<Instruction>) {
        //val height = rows.size
        private val width = rows[0].size
        private var curPos = Point(rows[0].indexOfFirst { it == '.' }, 0)
        private var direction = Right

        private fun xRangeFor(y: Int): IntRange =
            rows[y].let { row -> row.indexOfFirst { it != ' ' }..row.indexOfLast { it != ' ' } }

        private fun yRangeFor(x: Int): IntRange =
            rows.indexOfFirst { it[x] != ' ' }..rows.indexOfLast { it[x] != ' ' }

        private fun get(p: Point) = rows[p.y][p.x]

        fun toCube(isTest: Boolean): Cube {
            if (isTest) {
                val s = width / 4
                return Cube(
                    listOf(
                        takeSide(2, 0, s),
                        takeSide(3, 2, s), //.turn180(),
                        takeSide(2, 1, s),
                        takeSide(0, 1, s), //.turn180(),
                        takeSide(1, 1, s), //.turn90(),
                        takeSide(2, 2, s)
                    ), instructions
                )
            } else {
                val s = width / 3
                return Cube(
                    listOf(
                        takeSide(1, 0, s),
                        takeSide(2, 0, s),
                        takeSide(1, 1, s),
                        takeSide(0, 3, s).turn90(),
                        takeSide(0, 2, s).turn180(),
                        takeSide(1, 2, s)
                    ), instructions
                )
            }
        }

        private fun takeSide(x: Int, y: Int, size: Int): Rectangle =
            Rectangle(Point(x*size, y*size), 0, (y * size until y * size + size).map { yy ->
                (x * size until x * size + size).map { x ->
                    rows[yy][x]
                }
            })


        fun invoke(): Int {
            instructions.forEach { instruction ->
                run repeatBlock@{
                    repeat(instruction.count) {
                        val newPos = curPos.next(direction, xRangeFor(curPos.y), yRangeFor(curPos.x))
                        if (get(newPos) == '.') curPos = newPos else return@repeatBlock
                    }
                }

                direction = direction.turn(instruction.turn)
            }
            return 1000 * (curPos.y + 1) + 4 * (curPos.x + 1) + direction.id
        }


    }


    fun parseInput(lines: List<String>): Board {
        val board = lines.take(lines.size - 2)
        val width = board.maxOf { it.length }
        val rows = board.map { row ->
            row.padEnd(width).map { it }
        }

        val instructions = """(\d+)([LR]?)""".toRegex().findAll(lines.last()).map {
            Instruction(it.groupValues[1].toInt(),
                if (it.groupValues[2].isEmpty()) Turn.None
                else if (it.groupValues[2][0] == 'R') Clockwise
                else Counterclockwise)
        }
        return Board(rows, instructions.toList())
    }

}