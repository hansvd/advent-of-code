package aoc2021

import shared.Input

fun tryParseInt(s:String):Int?{
    return try {
        Integer.parseInt(s)
    } catch(e:Exception) {
        null
    }
}

fun main() {

    val slice= mutableListOf<Int>()
    var incr = 0

    Input.useLines(2021,1) {
        it.mapNotNull{ tryParseInt(it) }.forEach { cur ->
            if (slice.size == 3) {
                val sum = slice.sum()
                slice.removeAt(0)
                slice.add(cur)
                if (slice.sum() > sum) incr++
            }
            else slice.add(cur)
        }
    }
    println(incr)
}

