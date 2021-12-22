object Day22 {
    fun IntRange.overlap(other: IntRange): IntRange =
        IntRange(maxOf(this.first, other.first), minOf(this.last, other.last))

    val IntRange.width: Int get() = this.last - this.first + 1
    fun IntRange.isOverlap(other: IntRange) = !overlap(other).isEmpty()
    fun IntRange.contains(other: IntRange) = first <= other.first && last >= other.last


    data class Instruction(val on: Boolean, val cuboid: Cuboid)

    data class Cuboid(val xr: IntRange, val yr: IntRange, val zr: IntRange) {
        constructor(x0: Int, x1: Int, y0: Int, y1: Int, z0: Int, z1: Int) : this(
            IntRange(x0, x1),
            IntRange(y0, y1), IntRange
                (z0, z1)
        )

        val volume get() = xr.width.toLong() * yr.width.toLong() * zr.width.toLong()

        fun isOverlap(other: Cuboid) =
            xr.isOverlap(other.xr) && yr.isOverlap(other.yr) && zr.isOverlap(other.zr)

        val isEmpty get() = xr.isEmpty() || yr.isEmpty() || zr.isEmpty()

        private fun intersect(other: Cuboid): Cuboid {
            return Cuboid(
                maxOf(xr.first, other.xr.first), minOf(xr.last, other.xr.last),
                maxOf(yr.first, other.yr.first), minOf(yr.last, other.yr.last),
                maxOf(zr.first, other.zr.first), minOf(zr.last, other.zr.last)
            )
        }

        fun removeOverlap(other: Cuboid): List<Cuboid> {
            if (!isOverlap(other)) return listOf(this)
            if (other.xr.contains(xr) && other.yr.contains(yr) && other.zr.contains(zr)) return listOf()

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
            // remove the overlap
            val rest = cuboids.filter { it.isOverlap(addition) }.fold(listOf(addition)) { result, c ->
                result.map { it.removeOverlap(c) }.flatten()
            }
            rest.forEach { if (!it.isEmpty) cuboids.add(it) }
        }

        fun subtract(subtraction: Cuboid) {
            if (cuboids.none { it.isOverlap(subtraction) }) return

            for (c in cuboids.toList()) {
                if (c.isOverlap(subtraction)) {
                    val s = c.removeOverlap(subtraction)
                    cuboids.remove(c)
                    cuboids.addAll(s)
                }
            }
            return
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
                toRange(match.groupValues[2]).overlap(range),
                toRange(match.groupValues[3]).overlap(range),
                toRange(match.groupValues[4]).overlap(range)
            )
        )
    }

    private fun toRange(str: String): IntRange = str
        .split("..")
        .let { (a, b) -> a.toInt()..b.toInt() }
}