package aoc2022.day13

object Day202213 {

    class PacketPair(private val left: Any, private val right: Any) {
        fun isRightOrder(): Boolean = compare(left, right) == -1
    }

    fun part1(lines: List<String>): Int {
        return lines.windowed(2, 3).map {
            PacketPair(parseLine(it[0]), parseLine(it[1]))
        }.mapIndexed { index, packetPair -> if (packetPair.isRightOrder()) index + 1 else 0 }.sum()
    }

    fun part2(lines: Sequence<String>): Int {
        val sep1 = parseLine("[[2]]")
        val sep2 =  parseLine("[[6]]")
        val l = (listOf(sep1,sep2)
            + lines.filter { it.isNotBlank() }.map { parseLine(it) }).sortedWith { a, b -> compare(a, b)
        }
        return (l.indexOfFirst { it == sep1 } + 1) * (l.indexOfFirst { it == sep2 } + 1)
    }


    private fun parseLine(line: String): Any {
        if (line == "[]") return listOf<Int>()
        if (line[0].isDigit()) return Integer.valueOf(line)

        var d = 0
        var s = 1
        val sub = mutableListOf<Any>()
        for (i in 1 until line.length - 1) {
            if (line[i] == '[') d += 1
            else if (line[i] == ']') {
                d -= 1
            } else if (line[i] == ',' && d == 0) {
                if (s < i) sub.add(parseLine(line.substring(s, i)))
                s = i + 1
            }
        }
        if (line.length > s && s < line.length - 1)
            sub.add(parseLine(line.substring(s, line.length - 1)))
        return sub
    }

    fun compare(left: Any?, right: Any?): Int {
        if (left is Int && right is Int) {
            if (left < right) return -1
            if (right < left) return 1
            return 0
        }
        if (left is List<*> && right is List<*>) {
            val nl = left.size
            val nr = right.size
            for (i in 0 until nl.coerceAtMost(nr)) {
                val c = compare(left[i], right[i])
                if (c != 0) return c
            }
            if (nl < nr) return -1
            if (nr < nl) return 1
            return 0
        }
        if (left is Int) return compare(listOf(left), right)
        return compare(left, listOf(right))
    }
}