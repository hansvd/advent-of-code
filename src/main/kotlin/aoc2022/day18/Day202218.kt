package aoc2022.day18

object Day202218 {

    data class Cube(val x: Int, val y: Int, val z: Int) {
        fun neighbours() = setOf(Cube(x-1,y,z),Cube(x+1,y,z),Cube(x,y-1,z),Cube(x,y+1,z),Cube(x,y,z-1),Cube(x,y,z+1))
    }

    private fun internalAirSurface(cubes: List<Cube>): Int {

        val xMin = cubes.minOf { it.x }
        val xMax = cubes.maxOf { it.x }
        val yMin = cubes.minOf { it.y }
        val yMax = cubes.maxOf { it.y }
        val zMin = cubes.minOf { it.z }
        val zMax = cubes.maxOf { it.z }

        val outside = mutableListOf(Cube(xMin-1,yMin-1,zMin-1))
        var iOutside = 0
        while (iOutside < outside.size)  {
            val c = outside[iOutside]
            outside.addAll(c.neighbours().filter { n ->
                 (n.x in xMin - 1 .. xMax + 1 && n.y in yMin - 1 .. yMax+1 && n.z in zMin -1 .. zMax+ 1)
                    && (cubes.none { it == n } && outside.none { it == n })
            })
            iOutside++
        }
        return cubes.flatMap {it.neighbours()}.filter { outside.contains(it) }.size
    }

    fun part1(lines: Sequence<String>): Int = surface(parseInput(lines))

    fun part2(lines: Sequence<String>): Int = internalAirSurface(parseInput(lines))

    fun parseInput(lines: Sequence<String>): List<Cube> =
        lines.map { it.split(',').map { n -> n.toInt() } }.map { Cube(it[0], it[1], it[2]) }.toList()


    private fun surface(cubes: List<Cube>): Int =
        cubes.size*6 - cubes.flatMap { c-> c.neighbours()}.filter { cubes.contains(it)}.size
}
