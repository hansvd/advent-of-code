import AdventX.adventXa
import AdventX.adventXb
import java.io.File

fun main() {
    File("inputX.txt").useLines {
        println(adventXa(it))
        println(adventXb(it))
    }
}

object AdventX {
    fun adventXa(lines: Sequence<String>): Int {
        return 0
    }

    fun adventXb(lines: Sequence<String>): Int {
        return 0
    }
}