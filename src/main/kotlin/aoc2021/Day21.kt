package aoc2021

import kotlin.math.max

object Day21 {

    fun part1(player1In: Player, player2In: Player): Int {
        val dice = Dice()
        var player1 = player1In
        var player2 = player2In
        do {
            player1 = player1.move(dice.next())

            if (player1.score >= 1000) {
                return player2.score * dice.turn
            }
            player2 = player2.move(dice.next())
            if (player2.score >= 1000) {
                return player1.score * dice.turn
            }

        } while (true)
    }

    fun part2(player1: Player, player2: Player): Long {
        val p = DiracPlay(player1, player2)
        p.play()
        return p.score()
    }

    class Dice {
        var value = 0
        var turn = 0
        fun next(): Int = (0..2).fold(0) { tot, _ ->
            turn += 1
            value += 1
            if (value > 100) value = 1
            tot + value
        }
    }

    data class Player(val pos: Int, val score: Int = 0) {
        fun move(dice: Int): Player =
            Player((pos + dice - 1) % 10 + 1, score + (pos + dice - 1) % 10 + 1)
    }

    val diracDiceValues: List<Int> =
        (1..3).map { i1 -> (1..3).map { i2 -> (1..3).map { i3 -> i1 + i2 + i3 } } }.flatten().flatten()

    data class DiracBoard(val player1: Player, val player2: Player, val player1Turn: Boolean = true) {

        val isDone get() = player1.score >= 21 || player2.score >= 21
        fun play(): List<DiracBoard> = if (player1Turn)
            diracDiceValues.map { DiracBoard(player1.move(it), player2.copy(), false) }
        else
            diracDiceValues.map { DiracBoard(player1.copy(), player2.move(it), true) }
    }

    class DiracPlay(player1: Player, player2: Player) {
        private val boards = mutableMapOf<DiracBoard, Long>()
        private val done = mutableListOf<Pair<DiracBoard, Long>>()

        init {
            val firstBoard = DiracBoard(player1, player2)
            boards[firstBoard] = 1
        }

        fun play() {
            do {
                for ((board, count) in boards.entries.toList()) {
                    boards.remove(board)
                    if (board.isDone) {
                        done.add(Pair(board, count))
                        continue
                    }
                    board.play().forEach {
                        boards[it] = (boards[it] ?: 0) + count
                    }
                }
            } while (boards.isNotEmpty())
        }

        fun score(): Long {
            val p1 = done.filter { it.first.player1.score >= 21 }.sumOf { it.second }
            val p2 = done.filter { it.first.player2.score >= 21 }.sumOf { it.second }
            return max(p1, p2)
        }

    }


}