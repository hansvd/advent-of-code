package aoc2021

object Day22 {
    fun IntRange.intersect(other: IntRange): IntRange =
        IntRange(maxOf(this.first, other.first), minOf(this.last, other.last))

    val IntRange.width: Int get() = this.last - this.first + 1
    fun IntRange.isOverlap(other: IntRange) = !intersect(other).isEmpty()
    fun IntRange.contains(other: IntRange) = first <= other.first && last >= other.last

    data class Instruction(val on: Boolean, val cuboid: Cuboid)

    data class Cuboid(val xr: IntRange, val yr: IntRange, val zr: IntRange) {

        val volume get() = xr.width.toLong() * yr.width.toLong() * zr.width.toLong()

        fun isOverlap(other: Cuboid) =
            xr.isOverlap(other.xr) && yr.isOverlap(other.yr) && zr.isOverlap(other.zr)

        val isEmpty get() = xr.isEmpty() || yr.isEmpty() || zr.isEmpty()

        private fun intersect(other: Cuboid): Cuboid = Cuboid(
            xr.intersect(other.xr),
            yr.intersect(other.yr),
            zr.intersect(other.zr)
        )

        fun subtract(other: Cuboid): List<Cuboid> {
            if (!isOverlap(other)) return listOf(this)
            if (other.xr.contains(xr) && other.yr.contains(yr) && other.zr.contains(zr)) return listOf()

            // take all parts outside the intersect of both
            val inters = intersect(other)
            return sequence {
                if (inters.xr.first > xr.first)
                    yield(Cuboid(IntRange(xr.first, inters.xr.first - 1), yr, zr))
                if (xr.last > inters.xr.last)
                    yield(Cuboid(IntRange(inters.xr.last + 1, xr.last), yr, zr))
                if (inters.yr.first > yr.first)
                    yield(Cuboid(inters.xr, IntRange(yr.first, inters.yr.first - 1), zr))
                if (yr.last > inters.yr.last)
                    yield(Cuboid(inters.xr, IntRange(inters.yr.last + 1, yr.last), zr))
                if (inters.zr.first > zr.first)
                    yield(Cuboid(inters.xr, inters.yr, IntRange(zr.first, inters.zr.first - 1)))
                if (zr.last > inters.zr.last)
                    yield(Cuboid(inters.xr, inters.yr, IntRange(inters.zr.last + 1, zr.last)))
            }.toList()
        }

    }


    class Reactor {
        private val cuboids = mutableSetOf<Cuboid>()

        fun add(addition: Cuboid) {
            // remove the overlap and add that
            cuboids.addAll(
                cuboids.filter { it.isOverlap(addition) }.fold(listOf(addition)) { result, cuboidsToAdd ->
                    result.map { it.subtract(cuboidsToAdd) }.flatten()
                }.filter { !it.isEmpty })
        }

        fun subtract(subtraction: Cuboid) {
            if (cuboids.none { it.isOverlap(subtraction) }) return

            cuboids.toList().forEach { c->
                if (c.isOverlap(subtraction)) {
                    cuboids.addAll(c.subtract(subtraction))
                    cuboids.remove(c)
                }
            }
        }

        fun cubeCount(): Long {
            return cuboids.sumOf { it.volume }
        }
    }


    fun invoke(lines: Sequence<String>, range: IntRange = IntRange(Integer.MIN_VALUE, Integer.MAX_VALUE)): Long =
        calculateCubes(parseInputLines(lines, range))

    private fun calculateCubes(instructions: Sequence<Instruction>): Long {
        val reactor = Reactor()

        instructions.forEach {
            if (it.on) reactor.add(it.cuboid) else reactor.subtract(it.cuboid)
        }
        return reactor.cubeCount()

    }


    private fun parseInputLines(input: Sequence<String>, range: IntRange): Sequence<Instruction> =
        input.map { parseInput(it, range) }

    private fun parseInput(line: String, range: IntRange): Instruction {
        val reg = """(on|off) x=(-?\d+..-?\d+),y=(-?\d+..-?\d+),z=(-?\d+..-?\d+)""".toRegex()
        val match = reg.matchEntire(line)
        require(match != null)

        return Instruction(
            match.groupValues[1] == "on", Cuboid(
                toRange(match.groupValues[2]).intersect(range),
                toRange(match.groupValues[3]).intersect(range),
                toRange(match.groupValues[4]).intersect(range)
            )
        )
    }

    private fun toRange(str: String): IntRange = str
        .split("..")
        .let { (a, b) -> a.toInt()..b.toInt() }
}