package aoc2020.day01

object Day202001 {

    fun part01(lines: Sequence<String>): Int {
        val expenses = lines.map { it.toInt() }.toList()
        return expenses.indices.firstNotNullOfOrNull { i ->
            (i + 1 until expenses.size)
                .filter { j -> (expenses[i] + expenses[j] == 2020) }
                .map { j -> expenses[i] * expenses[j] }.firstOrNull()

        } ?: 0
    }

    fun part02(lines: Sequence<String>): Int {
        val expenses = lines.map { it.toInt() }.toList()
        return expenses.indices.firstNotNullOfOrNull { i ->
            (i + 1 until expenses.size).firstNotNullOfOrNull { j ->
                (j + 1 until expenses.size)
                    .filter { k -> (expenses[i] + expenses[j] + expenses[k] == 2020) }
                    .map { k -> expenses[i] * expenses[j] * expenses[k] }.firstOrNull()
            }
        } ?: 0
    }
}