package aoc2022.day10

object Day202210 {

    class Cpu {
        var cycle = 0
        var result = 0
        var x = 1

        fun nextCycle() {
            cycle += 1
            if (cycle >= 20 && ((cycle-20) % 40 == 0))
                result += cycle * x
        }
        fun noop() {
            nextCycle()
        }
        fun add(a:Int) {
            nextCycle()
            nextCycle()
            x += a
        }
    }
    fun part1(lines: Sequence<String>): Int {
        val cpu = Cpu()
        lines.forEach { s ->
            when {
                s.startsWith("noop") -> cpu.noop()
                s.startsWith("addx") -> cpu.add(Integer.valueOf(s.substringAfter(" ")))
            }
        }
        return cpu.result
    }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }
}