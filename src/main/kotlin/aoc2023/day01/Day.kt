package aoc2023.day01

object Day {

    fun part1(lines: List<String>) = lines.sumOf {
        digitsFromLine(it)
    }

    fun part2(lines: List<String>): Int = lines.sumOf { line ->
        val numbers = findNumbers(line)
        if (numbers.isEmpty())
            digitsFromLine(line)
        else
            firstNumber(numbers, line) * 10 + lastNumber(line, numbers)
    }

    private val numbers = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    private fun digitsFromLine(it: String): Int =
        (it.first { it.isDigit() }.code - '0'.code) * 10 +
                it.last { it.isDigit() }.code - '0'.code


    data class Number(val index: Int, val value: Int)
    private fun findNumbers(line: String) = line.mapIndexedNotNull { index, c ->
        val n = numbers.indexOfFirst { line.substring(index).startsWith(it) }
        if (n >= 0) Number(index, n + 1)
        else null
    }


    private fun lastNumber(line: String, numbers: List<Number>) =
       line.indexOfLast { it.isDigit() }.let { n ->
           if (n >= 0 && n > numbers.last().index)
               line[n].code - '0'.code
           else numbers.last().value
        }


    private fun firstNumber(numbers: List<Number>, line: String) =
        line.indexOfFirst { it.isDigit() }.let { n ->
            if (n >= 0 && n < numbers.first().index)
                line[n].code - '0'.code
            else numbers.first().value
    }


}