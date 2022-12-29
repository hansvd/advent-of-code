package aoc2022.day21

object Day202221 {

    interface Infix {
        fun calculate(): Infix
        fun containsUnknown() = false
    }

    data class Monkey(
        val name: String,
        var result: Long? = null,
        val waitFor: String = "",
        val andFor: String = "",
        val operation: Char = '0'
    )

    class Result(val r: Long) : Infix {
        override fun calculate(): Infix = this
        override fun toString(): String = r.toString()

        fun op(other: Long, operation: Char): Long = when (operation) {
            '*' -> r * other
            '/' -> r / other
            '+' -> r + other
            '-' -> r - other
            else -> {
                throw NotImplementedError()
            }
        }
    }

    class Node(val left: Infix, val right: Infix, val o: Char) : Infix {

        override fun toString(): String = "($left$o$right)"

        override fun calculate(): Infix {
            val l = left.calculate()
            val r = right.calculate()
            if (l is Result && r is Result)
                return Result(l.op(r.r, o))
            return Node(l,r,o)
        }

        override fun containsUnknown() = left.containsUnknown() || right.containsUnknown()
    }

    class Unknown : Infix {
        override fun calculate() = this
        override fun toString() = "??"
        override fun containsUnknown() = true
    }

    class Calculator(val monkeys: Map<String, Monkey>, val part2: Boolean = false) {
        private fun toInfix(monkeyName: String): Infix {
            if (part2 && monkeyName == "humn") return Unknown()
            val monkey: Monkey = monkeys[monkeyName]!!

            if (monkey.result != null) return Result(monkey.result!!)
            return Node(toInfix(monkey.waitFor), toInfix(monkey.andFor), monkey.operation)
        }

        private fun equation(left: Infix, right: Infix): Long {

            require(left.containsUnknown())
            require(!right.containsUnknown())

            if (left is Unknown && right is Result) return right.r // done
            if (left is Node) {
                val unknownInLeft = left.left.containsUnknown()
                return when (left.o) {
                    '+' -> {
                        if (unknownInLeft)
                            equation(left.left, Node(right, left.right, '-').calculate())
                        else
                            equation(left.right, Node(right, left.left, '-').calculate())
                    }

                    '-' -> {
                        if (unknownInLeft)
                            equation(left.left, Node(right, left.right, '+').calculate())
                        else
                            equation(left.right, Node(Result(-1), Node(right, left.left, '-'), '*').calculate())
                    }

                    '*' -> {
                        if (unknownInLeft)
                            equation(left.left, Node(right, left.right, '/').calculate())
                        else
                            equation(left.right, Node(right, left.left, '/').calculate())
                    }

                    '/' -> {
                        if (unknownInLeft)
                            equation(left.left, Node(right, left.right, '*').calculate())
                        else
                            equation(left.right, Node(right, left.left, '*').calculate()) // hoping X != 0

                    }

                    else -> throw NotImplementedError()
                }

            } else {
                throw UnsupportedOperationException()
            }
        }


        fun solve() = if (!part2)
            (toInfix("root").calculate() as Result).r
        else solveEquation()


        fun solveEquation(): Long {
            //trySolve(listOf("root", "humn"))
            val m = monkeys["root"]!!
            val left = toInfix(m.waitFor).calculate()
            val right = toInfix(m.andFor).calculate()


            return equation(
                if (left.containsUnknown()) left else right,
                if (left.containsUnknown()) right else left
            )
//            val f1 = generateFormula(m.waitFor)
//            val f2 = generateFormula(m.andFor)
//            equation(f1, f2)
//            println(left)
//            println(right)

//            return 0L

        }

    }

    fun part1(lines: Sequence<String>) = Calculator(parseLines(lines)).solve()

    fun part2(lines: Sequence<String>) = Calculator(parseLines(lines), true).solve()

    private fun parseLines(lines: Sequence<String>): Map<String, Monkey> {
        return lines.map { line ->
            val s = line.split(':')
            val name = s[0].trim()
            val op = s[1].trim().split(' ')
            if (op.size == 1)
                name to Monkey(name, result = op[0].toLong())
            else
                name to Monkey(
                    name, waitFor = op[0].trim(), andFor = op[2].trim(),
                    operation = op[1][0]
                )

        }.toMap()
    }
}
