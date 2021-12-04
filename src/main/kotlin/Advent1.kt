import java.io.File


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

    File("input/input1.txt").useLines {
        it.mapNotNull{tryParseInt(it)}.forEach { cur ->
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

