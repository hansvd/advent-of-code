import kotlin.math.abs

typealias RotatePar = Array<Day19.R>
typealias Bacon = Day19.Coordinates

object Day19 {


    //    pos = mapping of x,y,z  => 0 = x -> x, 1 = y -> x, 2 = z -> x
    //    m = multiply (-1,1)
    data class R(val pos: Int, val m: Int)

    // should be 24 possibilities
    val rotateParameters: List<RotatePar> = listOf(
        // positive x
        arrayOf(R(0, 1), R(1, 1), R(2, 1)),
        arrayOf(R(0, 1), R(2, -1), R(1, 1)),
        arrayOf(R(0, 1), R(1, -1), R(2, -1)),
        arrayOf(R(0, 1), R(2, 1), R(1, -1)),

        // negative x
        arrayOf(R(0, -1), R(1, -1), R(2, 1)),
        arrayOf(R(0, -1), R(2, 1), R(1, 1)),
        arrayOf(R(0, -1), R(1, 1), R(2, -1)),
        arrayOf(R(0, -1), R(2, -1), R(1, -1)),

        // positive y
        arrayOf(R(1, 1), R(2, 1), R(0, 1)),
        arrayOf(R(1, 1), R(0, -1), R(2, 1)),
        arrayOf(R(1, 1), R(2, -1), R(0, -1)),
        arrayOf(R(1, 1), R(0, 1), R(2, -1)),

        // negative y
        arrayOf(R(1, -1), R(2, -1), R(0, 1)),
        arrayOf(R(1, -1), R(0, 1), R(2, 1)),
        arrayOf(R(1, -1), R(2, 1), R(0, -1)),
        arrayOf(R(1, -1), R(0, -1), R(2, -1)),

        // positive z
        arrayOf(R(2, 1), R(0, 1), R(1, 1)),
        arrayOf(R(2, 1), R(1, -1), R(0, 1)),
        arrayOf(R(2, 1), R(0, -1), R(1, -1)),
        arrayOf(R(2, 1), R(1, 1), R(0, -1)),

        // negative z
        arrayOf(R(2, -1), R(0, -1), R(1, 1)),
        arrayOf(R(2, -1), R(1, 1), R(0, 1)),
        arrayOf(R(2, -1), R(0, 1), R(1, -1)),
        arrayOf(R(2, -1), R(1, -1), R(0, -1)),
    )

    fun List<RotatePar>.rotations(c: Coordinates) = this.map { r -> c.rotate(r) }
    fun Int.distance(o:Int):Int {
        if (o > this) return abs(o - this)
        return abs(this - o)
    }

    fun Bacon.match(o: Bacon) = samePosition(o)

    data class Coordinates(val x: Int, val y: Int, val z: Int) {
        val isOrigin = x == 0 && y == 0 && z == 0
        private val pos = arrayOf(x, y, z)

        fun rotate(r: RotatePar) = Coordinates(pos[r[0].pos] * r[0].m, pos[r[1].pos] * r[1].m, pos[r[2].pos] * r[2].m)
        fun plus(o: Coordinates) = Coordinates(x + o.x, y + o.y, z + o.z)
        fun minus(o: Coordinates) = Coordinates(x - o.x, y - o.y, z - o.z)

        fun samePosition(o: Coordinates): Boolean =
            rotateParameters.rotations(o).any { r -> (x == r.x && y == r.y && z == r.z) }

        fun delta(o: Coordinates): Coordinates = Coordinates(x - o.x, y - o.y, z - o.z)
        fun distance(d: Coordinates): Int = x.distance(d.x) + y.distance(d.y) + z.distance(d.z)
    }

    fun merge(scanners: List<Scanner>) {
        do {
            val done = scanners.drop(1).filter{ it.coordinates.isOrigin }.fold(false) { d, s2 ->
                scanners[0].merge(s2) || d }
        } while (done)
    }

    class Scanner(var coordinates: Coordinates = Coordinates(0, 0, 0), var bacons: Set<Bacon>) {

        fun merge(o: Scanner):Boolean {
            val (delta,r) = findDelta(o) ?: return false
            o.coordinates = if(coordinates.isOrigin) delta else  coordinates.minus(delta)

            bacons = bacons + o.bacons.map { it.rotate(r).plus(delta)}
            return true
        }

        private fun findDelta(o: Scanner): Pair<Coordinates,RotatePar>? {
            rotateParameters.forEach { r ->
                val deltaCount = mutableMapOf<Coordinates, Int>()
                bacons.forEach { bacon ->
                    o.bacons.forEach { otherBacon ->
                        val rotation = otherBacon.rotate(r)
                        val delta = bacon.delta(rotation)
                        deltaCount[delta] = (deltaCount[delta] ?: 0) + 1
                        if ((deltaCount[delta] ?: 0) >= 12)
                            return Pair(delta,r)
                    }
                }
            }
            return null
        }
    }

    fun maxDistance(scanners: List<Scanner>) = scanners.maxOf { s1 ->
         scanners.maxOf { s1.coordinates.distance(it.coordinates) }
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
            else Bacon(coord[0].toInt(), coord[1].toInt(), coord[2].toInt())
        }.toSet()
        return Scanner(bacons = bacons)
    }
}