package aoc2022.day11

object Day202211 {

    class Monkey(
        private val id: Int,
        private val items: MutableList<Long>,
        private val operation: List<String>,
        private val testDiv: Int,
        private val trueId: Int,
        private val falseId: Int
    ) {
        var inspectNb = 0L

        fun inspect(monkeys: List<Monkey>, trust:Boolean) {
            val scale = monkeys.map { it.testDiv }.fold(1) { acc,i -> acc*i}
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
            if (operation[1] == "+") return i1+i2
            else return i1*i2
        }

        companion object {
            fun parse(lines: List<String>): Monkey {
                val id = Integer.valueOf(lines[0].substringAfter(' ').trimEnd(':'))
                val items = lines[1].substringAfter("items: ").split(",").map { Integer.valueOf(it.trim()).toLong() }
                val op = lines[2].substringAfter("new = ").split(' ')
                val testDiv = Integer.valueOf(lines[3].substringAfterLast(' '))
                val trueId = Integer.valueOf(lines[4].substringAfterLast(' '))
                val falseId = Integer.valueOf(lines[5].substringAfterLast(' '))
                return Monkey(id, items.toMutableList(), op, testDiv, trueId, falseId)
            }
        }
    }

    fun part1(lines: List<String>): Long {
        val monkeys = parseInput(lines)
        repeat(20) {
            for (m in monkeys) {
                m.inspect(monkeys,true)
            }
        }
        val r = monkeys.map { it.inspectNb }.sortedDescending().take(2)
        return r[0] * r[1]
    }

    fun part2(lines: List<String>): Long {
        val monkeys = parseInput(lines)
        for (i in 0 until 10_000) {
            for (m in monkeys) {
                m.inspect(monkeys,false)
            }
        }
        val r = monkeys.map { it.inspectNb }.sortedDescending().take(2)
        return r[0] * r[1]
    }

    fun parseInput(lines: List<String>): List<Monkey> {
        var monkeyStart = -1
        return sequence {
            lines.forEachIndexed { index, s ->
                if (s.startsWith("Monkey")) {
                    if (monkeyStart >= 0) yield(Monkey.parse(lines.subList(monkeyStart, index - 1)))
                    monkeyStart = index
                }
            }
            yield(Monkey.parse(lines.subList(monkeyStart, lines.size)))
        }.toList()
    }
}