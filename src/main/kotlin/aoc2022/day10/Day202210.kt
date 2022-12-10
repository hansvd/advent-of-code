package aoc2022.day10

object Day202210 {

    class Cpu {
        private var cycle = 0
        private var x:Int = 1
        var result = 0

        private var image = ""

        private fun nextCycle() {
            cycle += 1
            var i = (image.length+1) % 41
            if (i == 0) {
                image += "\n"
                i = 1
            }

            image += if (i in x..x+2) "#" else "."
            if ((cycle-20) % 40 == 0)
                result += cycle * x
        }

        fun add(a:Int) {
            nextCycle()
            nextCycle()
            x += a
        }

        fun handleInput(lines: Sequence<String>) {
            lines.forEach { s ->
                when {
                    s.startsWith("noop") -> nextCycle()
                    s.startsWith("addx") -> add(Integer.valueOf(s.substringAfter(" ")))
                }
            }
        }
        override fun toString():String =image
    }

    fun part1(lines: Sequence<String>): Int = Cpu().also { it.handleInput(lines) }.result
    fun part2(lines: Sequence<String>): String = Cpu().also { it.handleInput(lines) }.toString()
}