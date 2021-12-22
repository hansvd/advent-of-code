object Day22 {
    data class Instruction(val on: Boolean, val cuboid: Cuboid)
    data class Cube(val x: Int, val y: Int, val z: Int)
    data class Cuboid(val xr: IntRange, val yr: IntRange, val zr: IntRange) {
        val volume get() = xr.width.toLong() * yr.width.toLong() * zr.width.toLong()
    }

    fun IntRange.overlap(other: IntRange): IntRange =
        IntRange(maxOf(this.first, other.first), minOf(this.last, other.last))

    val IntRange.width: Int get() = this.last - this.first + 1

    class Reactor {
        val cuboids = mutableSetOf<Cuboid>()

        fun add(cuboid: Cuboid) {
            cuboids.add(cuboid)
        }

        fun subtract(cuboid: Cuboid) {

        }

        fun cubeCount(): Long {
            return cuboids.sumOf { it.volume }
        }
    }


    fun invoke(lines: Sequence<String>, range: IntRange = IntRange(Integer.MIN_VALUE,Integer.MAX_VALUE)): Long {
        val input = parseInputLines(lines, range)
        return calculateCubes(input)
        //val r = mutableSetOf<Cube>()

    //        input.forEach { i ->
//
//            i.cuboid.xr.forEach { x ->
//                i.cuboid.yr.forEach { y ->
//                    i.cuboid.zr.forEach { z ->
//                        if (i.on) {
//                            r.add(Cube(x, y, z))
//                        } else {
//                            r.remove(Cube(x, y, z))
//                        }
//                    }
//                }
//            }
//        }
//        return r.size
    }

    fun calculateCubes(instructions: Sequence<Instruction>): Long {
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