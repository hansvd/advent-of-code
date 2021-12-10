import Day10.part1
import Day10.part2
import java.io.File


typealias Stack<T> = MutableList<T>
fun <T> Stack<T>.push(item: T) = add(item)
fun <T> Stack<T>.pop(): T? = if (isNotEmpty()) removeAt(lastIndex) else null
fun <T> Stack<T>.peek(): T? = if (isNotEmpty()) this[lastIndex] else null

fun main() {
    File("input/input10.txt").useLines {
        println(part1(it))
    }
    File("input/input10.txt").useLines {
         println(part2(it))
    }
}

object Day10 {

    class ChunkDelimiter(val open:Char, val close:Char, val score:Long, val completionScore:Long)
    val delimiters = listOf(
        ChunkDelimiter('(',')',3,1),
        ChunkDelimiter('[',']',57,2),
        ChunkDelimiter('{','}',1197,3),
        ChunkDelimiter('<','>',25137,4))


    fun part1(lines: Sequence<String>): Long {
          return lines.fold(0) { score, line -> score + errorScore(line)}
    }



    fun part2(lines: Sequence<String>): Long {

        val scores = lines.map { line -> completionScore(line) }.filter { it != 0L }.sorted().toList()
        if (scores.size == 0) return 0
        return scores[scores.size/2]
    }

    private fun errorScore(line: String): Long {
        val chunks:Stack<ChunkDelimiter> = mutableListOf()
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
    private fun completionScore(line: String): Long {
        val chunks:Stack<ChunkDelimiter> = mutableListOf()
        for (c in line) {
            val chunk = delimiters.firstOrNull { it.open == c }
            if (chunk != null) { chunks.push(chunk); continue }
            val chunkClose = delimiters.firstOrNull {it.close == c} ?: continue
            val ch = chunks.peek() ?: return 0
            if (ch.close != chunkClose.close) return 0
            chunks.pop()
        }

        val score = chunks.reversed().fold(0L) { score, c -> score * 5L + c.completionScore}
        return score
    }
}