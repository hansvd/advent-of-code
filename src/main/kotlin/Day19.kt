object Day19 {

    // should be 24 possibilities
    data class R(val pos:Int, val m:Int)
    val rotateParameters:List<Array<R>> = listOf(
        // positive x
        arrayOf(R(0,1), R(1,1), R(2,1)),
        arrayOf(R(0,1), R(2,-1), R(1,1)),
        arrayOf(R(0,1), R(1,-1), R(2,-1)),
        arrayOf(R(0,1), R(2,1), R(1,-1)),

        // negative x
        arrayOf(R(0,-1), R(1,-1), R(2,1)),
        arrayOf(R(0,-1), R(2,1), R(1,1)),
        arrayOf(R(0,-1), R(1,1), R(2,-1)),
        arrayOf(R(0,-1), R(2,-1), R(1,-1)),

        // positive y
        arrayOf(R(1,1), R(2,1), R(0,1)),
        arrayOf(R(1,1), R(0,-1), R(2,1)),
        arrayOf(R(1,1), R(2,-1), R(0,-1)),
        arrayOf(R(1,1), R(0,1), R(2,-1)),

        // negative y
        arrayOf(R(1,-1), R(2,-1), R(0,1)),
        arrayOf(R(1,-1), R(0,1), R(2,1)),
        arrayOf(R(1,-1), R(2,1), R(0,-1)),
        arrayOf(R(1,-1), R(0,-1), R(2,-1)),

        // positive z
        arrayOf(R(2,1), R(0,1), R(1,1)),
        arrayOf(R(2,1), R(1,-1), R(0,1)),
        arrayOf(R(2,1), R(0,-1), R(1,-1)),
        arrayOf(R(2,1), R(1,1), R(0,-1)),

        // negative z
        arrayOf(R(2,-1), R(0,-1), R(1,1)),
        arrayOf(R(2,-1), R(1,1), R(0,1)),
        arrayOf(R(2,-1), R(0,1), R(1,-1)),
        arrayOf(R(2,-1), R(1,-1), R(0,-1)),
    )

    fun List<Array<R>>.rotations(c:Coordinates) = this.map { r -> c.rotate(r) }

    data class Coordinates(val x: Int, val y: Int, val z: Int) {
        private val pos = arrayOf(x, y, z)

        fun rotate(r: Array<R>) = Coordinates(pos[r[0].pos] * r[0].m ,pos[r[1].pos] * r[1].m,pos[r[2].pos] * r[2].m)


        fun samePosition(o: Coordinates): Boolean =
            rotateParameters.rotations(o).any { r ->  (x == r.x && y == r.y && z == r.z) }

        fun delta(o:Coordinates):Coordinates = Coordinates(x-o.x,y-o.y,z-o.z)

       fun possibleDeltas(o:Coordinates):List<Coordinates> = rotateParameters.rotations(o).map { o -> delta(o) }

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