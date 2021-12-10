import Advent7.advent7a
import Advent7.advent7b
import java.io.File
import kotlin.math.abs

fun main() {
    val input = File("input/input7.txt").readText()
        println(advent7a(input))
        println(advent7b(input))
}

object Advent7 {
    fun advent7a(input:String): Int {
        val list = input.split(',').mapNotNull { tryParseInt(it) }
        if (list.isEmpty()) return 0
        return (0..list.maxOf { it }).fold(Int.MAX_VALUE) { min, i -> minOf(min, list.sumOf { abs(it - i) }) }
    }

    fun advent7b(input:String): Int {
        val list = input.split(',').mapNotNull { tryParseInt(it) }
        if (list.isEmpty()) return 0

        return (0..list.maxOf { it }).fold(Int.MAX_VALUE) { min, i -> minOf(min, list.sumOf { gaussSum(abs(it - i)) }) }
    }

    fun gaussSum(d:Int):Int = d*(d+1)/2

}