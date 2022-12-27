package aoc2022.day21

object Day202221 {


    class Monkey(
        val name: String,
        var result: Long? = null,
        val waitFor: String = "",
        val andFor: String = "",
        val operation: Char = '0'
    )
    class Calculator(val monkeys: Map<String, Monkey>) {
        fun solve1(): Long {
            while (true) {
                if (monkeys["root"]?.result != null)
                    return monkeys["root"]?.result!!
                trySolve(listOf())
            }
        }
        fun solve2(): Long {
            var hum = 0L
            while (true) {
                trySolve(listOf("root","humn"))
                if (hum != 0L) return hum
                if (monkeys["humn"]?.result != null)
                    return monkeys["root"]?.result!!

                //humn
                for (m in monkeys.values.filter { it.result == null }) calc(m)
            }
        }
        fun trySolve(exclude:List<String>) {
            var doneSomething: Boolean
            do {
                doneSomething = false
                for (m in monkeys.values.filter { it.result == null  && !exclude.contains(it.name) })
                    doneSomething = calc(m) || doneSomething
            }   while (doneSomething)
        }

        private fun calc(m: Monkey):Boolean {
            val m1 = monkeys[m.waitFor]?.result
            val m2 = monkeys[m.andFor]?.result
            if (m1 == null || m2 == null) return false
            m.result = when (m.operation) {
                '*' -> m1 * m2
                '/' -> m1 / m2
                '+' -> m1 + m2
                '-' -> m1 - m2
                else -> null
            }
            return m.result != null
        }
    }

    fun part1(lines: Sequence<String>) = Calculator(parseLines(lines)).solve1()

    fun part2(lines: Sequence<String>) = Calculator(parseLines(lines)).solve2()

    private fun parseLines(lines: Sequence<String>): Map<String,Monkey> {
        return lines.map { line ->
            val s = line.split(':')
            val name = s[0].trim()
            val op = s[1].trim().split(' ')
            if (op.size == 1)
                name to Monkey(name, result = op[0].toLong())
            else
                name to Monkey(name, waitFor = op[0].trim(), andFor = op[2].trim(),
                    operation =op[1][0])

        }.toMap()
    }
}