import kotlin.math.max

object Day21 {
    data class Player(val pos: Int, val score: Int = 0) {

        fun move(dice: Int): Player =
            Player(
                (pos + dice - 1) % 10 + 1,
                score + (pos + dice - 1) % 10 + 1,
            )
        override fun toString(): String {
            return "$pos, $score"
        }
    }



    class Dice {
        var value = 0
        var turn = 0
        fun next(): Int {
            var tot = 0
            for (i in 0..2) {
                turn += 1
                value += 1
                if (value > 100) value = 1
                tot += value
            }
            return tot
        }
    }

    val diracDiceValues: List<Int> =
        (1..3).map { i1 -> (1..3).map { i2 -> (1..3).map { i3 -> i1 + i2 + i3 } } }.flatten().flatten()


    data class DiracBoard(val player1: Player, val player2: Player, val player1Turn:Boolean=true) {



        // turn = 0..2 > play 1 throws
        val isDone get() = player1.score >= 21 || player2.score >= 21
        fun play(): List<DiracBoard> {

            if (player1Turn) {

                return diracDiceValues.map { DiracBoard(player1.move(it), player2.copy(), !player1Turn) }
            }
            else {

                return diracDiceValues.map { DiracBoard(player1.copy(), player2.move(it), !player1Turn) }

            }
        }

        override fun toString(): String {
            return "$player1; $player2"
        }

    }

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


    class DiracPlay(player1: Player, player2: Player) {
        val boards = mutableMapOf<DiracBoard, Long>()
        val done = mutableListOf<Pair<DiracBoard,Long>>()

        init {
            val firstBoard = DiracBoard(player1, player2)
            boards[firstBoard] = 1
        }

        fun play() {

            do {

                for (entry in boards.entries.toList()) {
                    val b = entry.key
                    boards.remove(b)

                    if (b.isDone) {
                        done.add(Pair(entry.key,entry.value))
                        continue
                    }
                    play(b,entry.value)

                }

                score()

            } while (boards.isNotEmpty())


        }

        private fun play(b: DiracBoard, count:Long) {
            val newBoards = b.play()

            newBoards.forEach {
                boards[it] = (boards[it] ?: 0) + count
            }

        }

        fun score(): Long {
            val count = done.sumOf { it.second }
            val p1 = done.filter { it.first.player1.score >= 21 }.sumOf { it.second }
            val p2 = done.filter { it.first.player2.score >= 21 }.sumOf { it.second }

            return max(p1, p2)
        }

    }


}