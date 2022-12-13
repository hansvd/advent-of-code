package aoc2022.day13

object Day202213 {

    class PacketPair(val left:Any, val right:Any) {
        fun isRightOrder():Boolean {
            return compare(left,right) == true
        }
        companion object {

        }
    }
    fun part1(lines: List<String>): Int {
        return lines.windowed(2,3).map {
            PacketPair(parseLine(it[0]), parseLine(it[1]))
        }.mapIndexed { index, packetPair -> if (packetPair.isRightOrder()) index+1 else 0}.sum()
    }

    fun part2(lines: Sequence<String>): Int {
        val l = listOf(parseLine("[[2]]"),
            parseLine("[[6]]")) + lines.filter {it.isNotBlank()}.map { parseLine(it) }


        val l2 = l.sortedWith(MyComparator)
        val i1 = 1 + l2.indexOfFirst {
            if (it is List<*> && it.size == 1) {
                val i0 = it[0]
                (i0 is List<*> && i0.size == 1 &&  i0[0] == 6)
            }
            else false
        }
        val i2 = 1 + l2.indexOfFirst {
            if (it is List<*> && it.size == 1) {
                val i0 = it[0]
                (i0 is List<*> && i0.size == 1 &&  i0[0] == 2)
            }
            else false
        }
        return i1 * i2
    }

    class MyComparator {

        companion object : Comparator<Any> {
            override fun compare(a: Any, b: Any): Int  {
                val c = Day202213.compare(a,b)
                if (c == true) return -1
                if (c == false) return 1
                return 0
            }
        }
    }


    fun parseLine(line:String):Any {
        if (line == "[]") return listOf<Int>()
        if (line[0].isDigit()) return Integer.valueOf(line)

        var d = 0
        var s = 1
        val sub = mutableListOf<Any>()
        for (i in 1 until line.length-1) {
            if (line[i] == '[') d += 1
            else if (line[i] == ']') {
                d-=1
            }
            else if (line[i] == ',' && d == 0) {
                if (s < i) sub.add(parseLine(line.substring(s,i)))
                s = i+1
            }
        }
        if (line.length > s && s < line.length-1)
            sub.add(parseLine(line.substring(s,line.length-1)))
        return sub
    }

    fun compare(left:Any?, right:Any?): Boolean? {
        if (left is Int && right is Int) {
            if (left < right) return true
            if (right < left) return false
            return null
        }
        if (left is List<*> && right is List<*>) {
            val nl = left.size
            val nr = right.size
            for (i in 0 until nl.coerceAtMost(nr)) {
                val c = compare(left[i],right[i])
                if (c != null) return c
            }
            if (nl < nr) return true
            if (nr < nl) return false
            return null
        }
        if (left is Int) return compare(listOf(left),right)
        return compare(left,listOf(right))
    }
}