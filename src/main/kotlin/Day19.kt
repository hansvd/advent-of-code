data class R(val pos: Int, val m: Int)
typealias RotatePar = Array<R>
object Day19 {

//    pos = mapping of x,y,z  => 0 = x -> x, 1 = y -> x, 2 = z -> x
//    m = multiply (-1,1)

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

    data class Coordinates(val x: Int, val y: Int, val z: Int) {
        val isOrigin = x == 0 && y == 0 && z == 0
        private val pos = arrayOf(x, y, z)

        fun rotate(r: RotatePar) = Coordinates(pos[r[0].pos] * r[0].m, pos[r[1].pos] * r[1].m, pos[r[2].pos] * r[2].m)
        fun plus(o: Coordinates) = Coordinates(x + o.x, y + o.y, z + o.z)
        fun minus(o: Coordinates) = Coordinates(x - o.x, y - o.y, z - o.z)

        fun samePosition(o: Coordinates): Boolean =
            rotateParameters.rotations(o).any { r -> (x == r.x && y == r.y && z == r.z) }

        fun delta(o: Coordinates): Coordinates = Coordinates(x - o.x, y - o.y, z - o.z)


    }

    data class Bacon(val coordinates: Coordinates) {
        fun match(o: Bacon) = coordinates.samePosition(o.coordinates)
    }

    fun setOffsets(scanners: List<Scanner>) {
        var done = true
        while (done) {
            done = false
            scanners.drop(1).filter{ it.coordinates.isOrigin }.forEach {
                done = scanners[0].setOffsets(it) || done }

//            scanners.filter { !it.coordinates.isOrigin }.forEach { s ->
//                scanners.drop(0).filter { it.coordinates.isOrigin }.forEach { done = s.setOffsets(it) || done }
//            }
        }
    }

    class Scanner(var coordinates: Coordinates = Coordinates(0, 0, 0), var bacons: List<Bacon>) {



        fun setOffsets(o: Scanner):Boolean {
            val (delta,r) = findDelta(o) ?: return false
            o.coordinates = if(coordinates.isOrigin) delta else  coordinates.minus(delta)

            bacons = (bacons + o.bacons.map { Bacon(it.coordinates.rotate(r).plus(delta))}).distinct()
            return true
        }

        private fun findDelta(o: Scanner): Pair<Coordinates,RotatePar>? {
            rotateParameters.forEach { r ->
                val deltaCount = mutableMapOf<Coordinates, Int>()
                for (bacon in bacons) for (otherBacon in o.bacons) {
                    val rotation = otherBacon.coordinates.rotate(r)
                    val delta = bacon.coordinates.delta(rotation)
                    deltaCount[delta] = (deltaCount[delta] ?: 0) + 1
                    if ((deltaCount[delta] ?: 0) >= 12)
                        return Pair(delta,r)
                }
            }
            return null
        }
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