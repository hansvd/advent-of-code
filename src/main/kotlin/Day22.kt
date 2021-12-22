object Day22 {
    data class Instruction(val on: Boolean, val xr: IntRange, val yr: IntRange, val zr: IntRange)
    data class Vector3D(val x: Int, val y: Int, val z: Int) {

    }

    fun IntRange.overlap(other:IntRange):IntRange {
        val start = maxOf(this.start,other.start)
        var end = minOf(this.endInclusive, other.endInclusive)
       // if (end < start) end = start
        return IntRange(start, end)
    }
    fun part1(lines: Sequence<String>, range: IntRange): Int {
        val r = mutableSetOf<Vector3D>()
        val input = parseInputLines(lines).toList()
        input.forEach { input ->
            input.xr.overlap(range).forEach { x ->
                input.yr.overlap(range).forEach { y ->
                    input.zr.overlap(range).forEach { z ->
                        if (input.on) {
                            r.add(Vector3D(x, y, z))
                        } else {
                            r.remove(Vector3D(x, y, z))
                        }
                    }
                }
            }
        }
        return r.size
    }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }

    fun parseInputLines(input: Sequence<String>): Sequence<Instruction> =
        input.mapNotNull { parseInput(it) }

    fun parseInput(line: String): Instruction? {
        val reg = """(on|off) x=(-?\d+..-?\d+),y=(-?\d+..-?\d+),z=(-?\d+..-?\d+)""".toRegex()
        val match = reg.matchEntire(line)
        require(match!=null)


        return Instruction(
            match.groupValues[1] == "on",
            toRange(match.groupValues[2]),
            toRange(match.groupValues[3]),
            toRange(match.groupValues[4])
        )
    }

    fun toRange(str: String): IntRange = str
        .split("..")
        .let { (a, b) -> a.toInt()..b.toInt() }
}