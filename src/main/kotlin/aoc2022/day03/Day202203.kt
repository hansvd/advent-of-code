package aoc2022.day02

object Day202203 {

    fun Char.priority():Int {
        if (this in 'a'..'z') return this.code - 'a'.code + 1
        if (this in 'A'..'Z') return this.code - 'A'.code + 27
        return 0
    }
    class Rucksack(private val compartment1:String,
                   private val compartment2: String) {

        fun score():Int {
            return compartment1.toList().distinct().sumOf { c ->
                if (compartment2.contains(c))
                    c.priority()
                else 0
            }
        }
        companion object {
            fun create(lines: Sequence<String>): List<Rucksack> {
                return lines.map {
                    val d = it.length/2
                    Rucksack(it.take(d),it.drop(d))
                }.toList()
            }
        }
    }
    fun part1(lines: Sequence<String>): Int {
        val r = Rucksack.create(lines)
        return r.sumOf { it.score() }
    }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }
}