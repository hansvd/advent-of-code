package aoc2022.day02


private const val Rock = 'A'
private const val Paper = 'B'
private const val Scissors = 'C'

object Day202202 {

    data class Round(val other: Char, val you: Char) {
        private fun score(other: Char, you: Char): Int =
            mapOf(
                Rock to Rock to 3 + 1,
                Rock to Paper to 0 + 1,
                Rock to Scissors to 6 + 1,
                Paper to Rock to 6 + 2,
                Paper to Paper to 3 + 2,
                Paper to Scissors to 0 + 2,
                Scissors to Rock to 0 + 3,
                Scissors to Paper to 6 + 3,
                Scissors to Scissors to 3 + 3
            )[you to other]!!

        fun score1(): Int = score(other, (you.code - 'X'.code + Rock.code).toChar())

        fun score2(): Int {

            val play = mapOf(
                Rock to 'X' to Scissors,
                Rock to 'Y' to Rock,
                Rock to 'Z' to Paper,
                Paper to 'X' to Rock,
                Paper to 'Y' to Paper,
                Paper to 'Z' to Scissors,
                Scissors to 'X' to Paper,
                Scissors to 'Y' to Scissors,
                Scissors to 'Z' to Rock
            )[other to you]!!

            return score(other, play)
        }
    }


    class Game(lines: Sequence<String>) {
        private val rounds: List<Round>

        init {
            rounds = lines.map { Round(it[0], it[2]) }.toList()
        }
        val part1 = rounds.sumOf { it.score1() }
        val part2 = rounds.sumOf { it.score2() }
    }

    fun part1(lines: Sequence<String>): Int = Game(lines).part1

    fun part2(lines: Sequence<String>): Int = Game(lines).part2
}