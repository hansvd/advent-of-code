package aoc2022.day25
import kotlin.math.pow

object Day202225 {

    fun part1(lines: Sequence<String>): String = decToSnafu(lines.map { snafuToDec(it)}.sum())

    private fun Int.pow(exp:Int) = this.toDouble().pow(exp).toLong()

    fun snafuToDec(snafu: String): Long =
        snafu.reversed().foldIndexed(0L) { index, r, el  ->
            when (el) {
                '1' -> r + 1 * 5.pow(index)
                '2' -> r+ 2 * 5.pow(index)
                '-' -> r - 1 * 5.pow(index)
                '=' -> r - 2 * 5.pow(index)
                else -> r
            }
        }
    fun decToSnafu(dec: Long): String {
        val result = mutableListOf<Long>()
        var d = dec
        do  {
            val c = d % 5
            result.add(c)
            d /= 5
        } while (d > 0)
        result.add(0)

        (0 until result.size).forEach {i ->
            if (result[i] > 4) {
                result[i] -= 5L
                result[i+1] += 1L
            }
            when {
                result[i] == 3L -> {
                    result[i] = -2
                    result[i+1] += 1L
                }
                result[i] == 4L -> {
                    result[i] = -1
                    result[i+1] += 1L
                }
            }
        }
        return result.reversed().fold("") { a, b -> a + when(b) {
            -2L -> "="
            -1L -> "-"
            else -> b
        } }.trimStart('0')
    }
}