import Day10.part1
import Day10.part2
import java.io.File
import java.util.*

fun main() {
    File("input/input10.txt").useLines {
        println(part1(it))
    }
    File("input/input10.txt").useLines {
         println(part2(it))
    }
}

object Day10 {

    class ChunkDelimiter(val open:Char, val close:Char, val score:Long)
    val delimiters = listOf(
        ChunkDelimiter('(',')',3),
        ChunkDelimiter('[',']',57),
        ChunkDelimiter('{','}',1197),
        ChunkDelimiter('<','>',25137))


    fun part1(lines: Sequence<String>): Long {
          return lines.fold(0) { score, line -> score + errorScore(line)}
    }

    private fun errorScore(line: String): Long {
        val chunks = Stack<ChunkDelimiter>()
        for (c in line) {
            val chunk = delimiters.firstOrNull { it.open == c }
            if (chunk != null) { chunks.push(chunk); continue }
            val chunkClose = delimiters.firstOrNull {it.close == c} ?: continue
            val ch = chunks.peek() ?: return chunkClose.score
            if (ch.close != chunkClose.close) return chunkClose.score
            chunks.pop()
        }
        return 0
    }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }
}