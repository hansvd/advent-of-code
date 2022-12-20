package aoc2022.day18

object Day202218 {

    class Cube(val x:Int, val y:Int, val z:Int)
    fun part1(lines: Sequence<String>): Int {
        val cubes = parseInput(lines)

        return cubes.sumOf {c ->
            (if (cubes.none { it.x == c.x - 1  && it.y == c.y && it.z ==c.z }) 1 else 0) +
                    (if (cubes.none { it.x == c.x + 1 && it.y == c.y && it.z ==c.z }) 1 else 0) +
                    (if (cubes.none { it.y == c.y - 1  && it.x == c.x && it.z ==c.z }) 1 else 0) +
                    (if (cubes.none { it.y == c.y + 1  && it.x == c.x && it.z ==c.z }) 1 else 0) +
                    (if (cubes.none { it.z == c.z - 1  && it.y == c.y && it.x ==c.x }) 1 else 0) +
                    (if (cubes.none { it.z == c.z + 1  && it.y == c.y && it.x ==c.x }) 1 else 0)
        }

    }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }

    fun parseInput(lines: Sequence<String>):List<Cube> =
        lines.map { it.split(',').map { n -> n.toInt() }}.map { Cube(it[0],it[1],it[2])}.toList()
}