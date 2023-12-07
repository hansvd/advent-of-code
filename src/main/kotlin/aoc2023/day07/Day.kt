package aoc2023.day07

object Day {

    val cards = listOf ('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
    fun Char.compareCard(other:Char) = cards.indexOf(this).compareTo(cards.indexOf(other)) * -1

    data class Hand(val cards:CharArray, val bid:Int) : Comparable<Hand> {
        val points: Int
            get() {
                val groups = cards.groupBy { it }
                return when {
                    groups.size == 1 -> 70 // full hand
                    groups.size == 2 && groups.any { it.value.size == 4 } -> 60 // four of a kind
                    groups.size == 2 && groups.any { it.value.size == 3 } -> 50 // full house
                    groups.size == 3 && groups.any { it.value.size == 3 } -> 40 // three of a kind
                    groups.size == 3 && groups.count { it.value.size == 2 } == 2 -> 30 // two pair
                    groups.size == 4 && groups.any { it.value.size == 2 } -> 20 // one pair
                    groups.size == 5 -> 10 // high card
                    else -> 0
                }
            }

        override fun compareTo(other: Hand): Int {
            val r = points.compareTo(other.points)
            if (r != 0) return r
            (0..4).forEach {
                val r2 = cards[it].compareCard(other.cards[it])
                if (r2 != 0) return r2
            }
            return 0
        }

    }
    fun part1(lines: List<String>):Int {
        val hands = parseInput(lines)
        return hands.sorted().mapIndexed { index, hand -> hand.bid * (index + 1) }.sum()
    }

    fun part2(lines: List<String>): Int = 0

    fun parseInput(lines: List<String>) = lines.map {Hand(it.substring(0, 5).toCharArray(), it.substring(6).toInt())}

}