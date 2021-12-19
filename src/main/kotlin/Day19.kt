object Day19 {
    val permutations:List<Array<Int>> = listOf(
        arrayOf(0, 1, 2),
        arrayOf(0, 2, 1),
        arrayOf(1, 0, 2),
        arrayOf(1, 2, 0),
        arrayOf(2, 0, 1),
        arrayOf(2, 1, 0)
    )
    val rotations = listOf(
        arrayOf(1,1,1),
        arrayOf(1,-1,1),
        arrayOf(1,1,-1),
        arrayOf(1,-1,-1),
        arrayOf(-1,1,1),
        arrayOf(-1,-1,1),
        arrayOf(-1,-1,-1),
        arrayOf(-1,-1,-1)
    )
    data class Coordinates(val x: Int, val y: Int, val z: Int) {
        val pos = arrayOf(x, y, z)
//        fun samePosition(o: Coordinates, m: Array<Int>): Boolean {
//            return rotations.any { r -> (pos[0] == o.pos[m[0]]*r[0] && pos[1] == o.pos[m[1]]*r[1] && pos[2] == o.pos[m[2]]*r[2]) }
//
//        }

        fun samePosition(o: Coordinates): Boolean = //permutations.any { p -> samePosition(o, p) }
            permutations.any { m -> rotations.any { r -> (x == o.pos[m[0]]*r[0] && y == o.pos[m[1]]*r[1] && z == o.pos[m[2]]*r[2]) } }

        fun delta(o:Coordinates, m:Array<Int>):Coordinates = Coordinates(x-o.pos[m[0]],y-o.pos[m[1]],x-o.pos[m[2]])
    }

    data class Bacon(val coordinates: Coordinates) {
        fun match(o: Bacon) = coordinates.samePosition(o.coordinates)
    }

    data class Scanner(val coordinates: Coordinates? = null, val bacons: List<Bacon>) {
        fun findMatchingBacons(other: Scanner): List<Pair<Bacon, Bacon>> {
            return bacons.map { b ->
                other.bacons.mapNotNull { o ->
                    if (b.match(o)) Pair(b, o) else null
                }
            }.flatten()
        }
    }

    fun part1(lines: Sequence<String>): Int {
        return 0
    }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }


    fun parseInput(lines: List<String>): List<Scanner> {
        // split per scanner
        val scannerStarts = lines.mapIndexedNotNull { index, s -> if (s.startsWith("--- scanner")) index else null }
        return scannerStarts.mapIndexed { i, start ->
            val end = if (i == scannerStarts.size - 1) lines.size else scannerStarts[i + 1]
            parseScannerInput(lines.subList(start, end))
        }
    }

    private fun parseScannerInput(subList: List<String>): Scanner {
        val bacons = subList.mapNotNull { l ->
            val coord = l.split(',')
            if (coord.size != 3) null
            else Bacon(Coordinates(coord[0].toInt(), coord[1].toInt(), coord[2].toInt()))
        }
        return Scanner(bacons = bacons)
    }
}