package aoc2021

import aoc2021.Day10.part1
import aoc2021.Day10.part2
import java.io.File


typealias Stack<T> = MutableList<T>
fun <T> Stack<T>.push(item: T) = add(item)
fun <T> Stack<T>.pop(): T? = if (isNotEmpty()) removeAt(lastIndex) else null
fun <T> Stack<T>.peek(): T? = if (isNotEmpty()) this[lastIndex] else null

fun main() {
    File("src/main/kotlin/aoc2021/input10.txt").useLines {
        println(part1(it))
    }
    File("src/main/kotlin/aoc2021/input10.txt").useLines {
         println(part2(it))
    }
}

object Day10 {

    class ChunkDelimiter(val open:Char, val close:Char, val score:Long, val completionScore:Long)
    private val delimiters = listOf(
        ChunkDelimiter('(',')',3,1),
        ChunkDelimiter('[',']',57,2),
        ChunkDelimiter('{','}',1197,3),
        ChunkDelimiter('<','>',25137,4)
    )


    fun part1(lines: Sequence<String>): Long {
          return lines.fold(0) { score, line -> score + errorScore(line) }
    }

    fun part2(lines: Sequence<String>): Long {

        val scores = lines.map { line -> completionScore(line) }.filter { it != 0L }.sorted().toList()
        if (scores.isEmpty()) return 0
        return scores[scores.size/2]
    }

    private fun errorScore(line: String): Long {
        val chunks: Stack<ChunkDelimiter> = mutableListOf()
        for (c in line) {
            val errorChunk = handleLine(chunks,c) ?: continue
            return errorChunk.score
        }
        return 0
    }

    private fun completionScore(line: String): Long {
        val chunks: Stack<ChunkDelimiter> = mutableListOf()
        for (c in line) {
            val errorChunk = handleLine(chunks, c)
            if (errorChunk != null) return 0
        }

        return chunks.reversed().fold(0L) { score, c -> score * 5L + c.completionScore }
    }

    private fun handleLine(chunks: Stack<ChunkDelimiter>, c:Char): ChunkDelimiter? {
        val chunk = delimiters.firstOrNull { it.open == c }
        if (chunk != null) { chunks.push(chunk); return null }
        val chunkClose = delimiters.firstOrNull {it.close == c} ?: return null
        val ch = chunks.peek() ?: return chunkClose
        if (ch.close != chunkClose.close) return chunkClose
        chunks.pop()
        return null
    }

}