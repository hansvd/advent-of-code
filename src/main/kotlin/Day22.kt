object Day22 {
    fun IntRange.overlap(other: IntRange): IntRange =
        IntRange(maxOf(this.first, other.first), minOf(this.last, other.last))
    val IntRange.width: Int get() = this.last - this.first + 1
    fun IntRange.isOverlap(other:IntRange) = !overlap(other).isEmpty()

    data class Instruction(val on: Boolean, val cuboid: Cuboid)
    //data class Cube(val x: Int, val y: Int, val z: Int)
    data class Cuboid(val xr: IntRange, val yr: IntRange, val zr: IntRange) {
        val volume get() = xr.width.toLong() * yr.width.toLong() * zr.width.toLong()

        fun isOverlap(other:Cuboid) =
            xr.isOverlap(other.xr) && yr.isOverlap(other.yr) && zr.isOverlap(other.zr)
        val isEmpty get() = xr.isEmpty() || yr.isEmpty() || zr.isEmpty()

        fun removeOverlap(other: Cuboid):Cuboid {
            // TODO
            return this
        }
    }



    class Reactor {
        private val cuboids = mutableSetOf<Cuboid>()

        fun add(addition: Cuboid) {
            // remove the overlap
            val rest = cuboids.filter { it.isOverlap(addition) }.fold(addition) { result, c ->
                addition.removeOverlap(c)
            }
            if (!rest.isEmpty) cuboids.add(rest)
        }

        fun subtract(subtraction: Cuboid) {
            if (cuboids.none { it.isOverlap(subtraction) }) return

            // TODO
            return
        }

        fun cubeCount(): Long {
            return cuboids.sumOf { it.volume }
        }
    }


    fun invoke(lines: Sequence<String>, range: IntRange = IntRange(Integer.MIN_VALUE,Integer.MAX_VALUE)): Long =
        calculateCubes( parseInputLines(lines, range))

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