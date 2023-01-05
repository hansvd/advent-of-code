package aoc2022.day22

import shared.Direction
import shared.Direction.*
import shared.Point
import shared.Rotate
import shared.Rotate.Clockwise
import shared.Rotate.Counterclockwise

object Day202222 {


    fun part1(lines: List<String>): Int = parseInput(lines).invoke()

    fun part2(lines: List<String>, isTest: Boolean): Int = parseInput(lines).toCube(isTest).invoke()

    data class Instruction(val count: Int, val rotate: Rotate)

    data class Rectangle(val start: Point, val rows: List<List<Char>>) {

        fun get(p: Point) = rows[p.y][p.x]

        fun toBoardPoint(pos: Point): Point = Point(pos.x + start.x, pos.y + start.y)

    }


    class Cube(
        private val sides: List<Rectangle>, val sideTransition: Map<Pair<Int, Direction>, Pair<Int, Int>>,
        private val instructions: List<Instruction>
    ) {
        private var state = State(0, Point(sides[0].rows[0].indexOfFirst { it == '.' }, 0), Right)
        private val sideWidth = sides[0].rows.size
        private val sideMax = sideWidth - 1


        inner class State(private val side: Int, private val pos: Point, private val direction: Direction) {

            fun get() = sides[side].get(pos)

            override fun toString(): String = "side=$side, pos=$pos, direction=$direction"

            fun result(): Int {
                val curPos = sides[side].toBoardPoint(pos)
                //println("Result for side=$side, pos=$pos -> $curPos, dir=${direction.id}")
                return 1000 * (curPos.y + 1) + 4 * (curPos.x + 1) + direction.id
            }

            fun turn(rotate: Rotate): State = State(
                side, pos,
                direction.rotate(rotate)
            )

            private val toX = -10
            private val toMaxMinX = -11
            private val toY = -12
            private val toMaxMinY = -13
            private fun mapToReal(vX: Int, curPos: Point): Int =
                if (vX >= 0) vX
                else when (vX) {
                    toX -> curPos.x
                    toY -> curPos.y
                    toMaxMinX -> sideMax - curPos.x
                    toMaxMinY -> sideMax - curPos.y
                    else -> throw NotImplementedError()
                }

            private val degreesDirectionToPosDirection = mapOf(
                Pair(0, Right) to Pair(Point(0, toY), Right),
                Pair(90, Right) to Pair(Point(toY, sideMax), Up),
                Pair(180, Right) to Pair(Point(sideMax, toMaxMinY), Left),
                Pair(270, Right) to Pair(Point(toMaxMinY, 0), Down),

                Pair(0, Left) to Pair(Point(sideMax, toY), Left),
                Pair(90, Left) to Pair(Point(toY, 0), Down),
                Pair(180, Left) to Pair(Point(0, toMaxMinY), Right),
                Pair(270, Left) to Pair(Point(toMaxMinY, sideMax), Up),

                Pair(0, Up) to Pair(Point(toX, sideMax), Up),
                Pair(90, Up) to Pair(Point(sideMax, toMaxMinX), Left),
                Pair(180, Up) to Pair(Point(toMaxMinX, 0), Down),
                Pair(270, Up) to Pair(Point(0, toX), Right),

                Pair(0, Down) to Pair(Point(toX, 0), Down),
                Pair(90, Down) to Pair(Point(0, toMaxMinX), Right),
                Pair(180, Down) to Pair(Point(toMaxMinX, sideMax), Up),
                Pair(270, Down) to Pair(Point(sideMax, toX), Left),
            )

            fun nextState(): State {
                val newPos = pos.next(direction)
                return if (newPos.x in 0..sideMax && newPos.y in 0..sideMax) State(side, newPos, direction)
                else {
                    val (newSide, degrees) = sideTransition[Pair(side, direction)]!!
                    val (newPosV, newDirection) = degreesDirectionToPosDirection[Pair(degrees, direction)]!!
                    return State(newSide, Point(mapToReal(newPosV.x, pos), mapToReal(newPosV.y, pos)), newDirection)
                }
            }

        }

        //val nextSides = mapOf(Pair(0, Right) to Pair(0, Right))
        fun invoke(): Int {

            instructions.forEach { instruction ->
                println(instruction)
                run repeatBlock@{
                    repeat(instruction.count) {
                        val newState = state.nextState()
                        if (newState.get() == '.')
                            state = newState
                        else
                            return@repeatBlock
                    }
                }
                state = state.turn(instruction.rotate)
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
                        takeSide(3, 2, s),
                        takeSide(2, 1, s),
                        takeSide(0, 1, s), //.turn180(),
                        takeSide(1, 1, s), //urn90(),
                        takeSide(2, 2, s)
                    ), mapOf(
                        Pair(0, Right) to Pair(1, 180),
                        Pair(0, Left) to Pair(4, 90),
                        Pair(0, Up) to Pair(3, 180),
                        Pair(0, Down) to Pair(2, 0),

                        Pair(1, Right) to Pair(0, 180),
                        Pair(1, Left) to Pair(5, 0),
                        Pair(1, Up) to Pair(2, 90),
                        Pair(1, Down) to Pair(3, 90),

                        Pair(2, Right) to Pair(1, 270),
                        Pair(2, Left) to Pair(4, 0),
                        Pair(2, Up) to Pair(0, 0),
                        Pair(2, Down) to Pair(5, 0),

                        Pair(3, Right) to Pair(4, 0),
                        Pair(3, Left) to Pair(1, 20),
                        Pair(3, Up) to Pair(0, 180),
                        Pair(3, Down) to Pair(5, 180),

                        Pair(4, Right) to Pair(2, 0),
                        Pair(4, Left) to Pair(3, 0),
                        Pair(4, Up) to Pair(0, 270),
                        Pair(4, Down) to Pair(5, 90),

                        Pair(5, Right) to Pair(1, 0),
                        Pair(5, Left) to Pair(4, 270),
                        Pair(5, Up) to Pair(2, 0),
                        Pair(5, Down) to Pair(3, 180),


                        ), instructions
                )
            } else {
                val s = width / 3
                return Cube(
                    listOf(
                        takeSide(1, 0, s),
                        takeSide(2, 0, s),
                        takeSide(1, 1, s),
                        takeSide(0, 3, s),
                        takeSide(0, 2, s),
                        takeSide(1, 2, s)
                    ), mapOf(
                        Pair(0, Right) to Pair(1, 0),
                        Pair(0, Left) to Pair(4, 180),
                        Pair(0, Up) to Pair(3, 270),
                        Pair(0, Down) to Pair(2, 0),

                        Pair(1, Right) to Pair(5, 180),
                        Pair(1, Left) to Pair(0, 0),
                        Pair(1, Up) to Pair(3, 0),
                        Pair(1, Down) to Pair(2, 270),

                        Pair(2, Right) to Pair(1, 90),
                        Pair(2, Left) to Pair(4, 90),
                        Pair(2, Up) to Pair(0, 0),
                        Pair(2, Down) to Pair(5, 0),

                        Pair(3, Right) to Pair(5, 90),
                        Pair(3, Left) to Pair(0, 90),
                        Pair(3, Up) to Pair(4, 0),
                        Pair(3, Down) to Pair(1, 0),

                        Pair(4, Right) to Pair(5, 0),
                        Pair(4, Left) to Pair(0, 180),
                        Pair(4, Up) to Pair(2, 270),
                        Pair(4, Down) to Pair(3, 0),

                        Pair(5, Right) to Pair(1, 180),
                        Pair(5, Left) to Pair(4, 0),
                        Pair(5, Up) to Pair(2, 0),
                        Pair(5, Down) to Pair(3, 270),

                        ), instructions
                )
            }
        }

        private fun takeSide(x: Int, y: Int, size: Int): Rectangle =
            Rectangle(Point(x * size, y * size), (y * size until y * size + size).map { yy ->
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

                direction = direction.rotate(instruction.rotate)
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
            Instruction(
                it.groupValues[1].toInt(),
                if (it.groupValues[2].isEmpty()) Rotate.None
                else if (it.groupValues[2][0] == 'R') Clockwise
                else Counterclockwise
            )
        }
        return Board(rows, instructions.toList())
    }

}