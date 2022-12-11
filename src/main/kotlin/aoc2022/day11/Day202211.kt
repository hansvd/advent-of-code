package aoc2022.day11

class Day202211(lines: List<String>, val trust: Boolean) {
    val monkeys: List<Monkey> = lines.chunked(7).map { parse(it) }
    val scale = monkeys.map { it.testDiv }.fold(1) { acc, i -> acc * i }.toLong()


    private fun parse(lines: List<String>): Monkey {
        val id = Integer.valueOf(lines[0].substringAfter(' ').trimEnd(':'))
        val items = lines[1].substringAfter("items: ").split(",").map { Integer.valueOf(it.trim()).toLong() }
        val op = lines[2].substringAfter("new = ").split(' ')
        val testDiv = Integer.valueOf(lines[3].substringAfterLast(' '))
        val trueId = Integer.valueOf(lines[4].substringAfterLast(' '))
        val falseId = Integer.valueOf(lines[5].substringAfterLast(' '))
        return Monkey(id, items.toMutableList(), op, testDiv, trueId, falseId)
    }

    inner class Monkey(
        private val id: Int,
        private val items: MutableList<Long>,
        private val operation: List<String>,
        val testDiv: Int,
        private val trueId: Int,
        private val falseId: Int
    ) {
        var inspectNb = 0L

        fun inspect() {

            for (item in items.toList()) {
                inspectNb++
                var i = calc(item)
                if (trust) i /= 3 else i %= scale
                val m = monkeys.firstOrNull { it.id == (if (i % testDiv == 0L) trueId else falseId) } ?: continue
                m.items.add(i)
                items.remove(item)
            }
        }

        private fun calc(item: Long): Long {
            val i1 = if (operation[0] == "old") item else Integer.valueOf(operation[0]).toLong()
            val i2 = if (operation[2] == "old") item else Integer.valueOf(operation[2]).toLong()
            return if (operation[1] == "+") i1 + i2 else i1 * i2
        }
    }

    private fun invoke(count: Int): Long {
        repeat(count) {
            for (m in monkeys) {
                m.inspect()
            }
        }
        val r = monkeys.map { it.inspectNb }.sortedDescending().take(2)
        return r[0] * r[1]
    }


    companion object {
        fun part1(lines: List<String>): Long = Day202211(lines, true).invoke(20)
        fun part2(lines: List<String>): Long = Day202211(lines, false).invoke(10_000)
    }
}